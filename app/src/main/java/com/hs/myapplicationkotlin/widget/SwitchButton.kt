package com.hs.myapplicationkotlin.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt
import com.desaysv.mvvm.utils.dpToPx
import com.hs.myapplicationkotlin.R

/**
 * @Description : 公共控件SwitchButton
 * @Date        : 2025/1/9 11:52
 * @Author      : uids0505
 */
class SwitchButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(
    context, attrs,
    defStyleAttr
) {

    private var thumbRadius = 0f // 小圆点半径
    private var thumbPosition = 0f // 小圆点的当前位置，范围 [0, 1]
    private var animator: ValueAnimator? = null // 动画对象
    private var backgroundRadius = 0f // 背景圆角半径
    private var trackHeight = 40f // 轨道高度


    var isChecked = false // 控件当前选中状态
        set(value) {
            field = value
            startThumbAnimation(value) // 切换状态时启动动画
            onCheckedChangeListener?.invoke(value)
        }

    // 回调监听器
    var onCheckedChangeListener: ((Boolean) -> Unit)? = null

    // 自定义属性：背景颜色（选中和未选中）
    private var checkedColor: Int = Color.GREEN
    private var uncheckedColor: Int = Color.GRAY
    // 圆点颜色
    private var thumbColor: Int = Color.WHITE
    // 画笔
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 配置颜色
//    @ColorInt
//    private var checkedColor: Int = Color.parseColor("#4CAF50") // 选中时背景颜色
//    @ColorInt
//    private var uncheckedColor: Int = Color.parseColor("#CCCCCC") // 未选中时背景颜色


    init {
        // 解析自定义属性
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton)
            checkedColor = typedArray.getColor(
                R.styleable.SwitchButton_checkedColor,
                Color.GREEN // 默认选中颜色：绿色
            )
            uncheckedColor = typedArray.getColor(
                R.styleable.SwitchButton_uncheckedColor,
                Color.GRAY // 默认未选中颜色：灰色
            )
            thumbColor = typedArray.getColor(
                R.styleable.SwitchButton_thumbColor,
                Color.WHITE // 默认圆点颜色：白色
            )
            isChecked = typedArray.getBoolean(
                R.styleable.SwitchButton_isChecked,
                false // 默认未选中状态
            )
            typedArray.recycle() // 回收 TypedArray
        }

        // 点击切换状态
        setOnClickListener {
            isChecked = !isChecked
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 设置控件的默认宽高
        val desiredWidth = dpToPx(100)
        val desiredHeight = dpToPx(50)

        // 确定控件的宽高，支持 wrap_content 和 match_parent
        val width = resolveSize(desiredWidth.toInt(), widthMeasureSpec)
        val height = resolveSize(desiredHeight.toInt(), heightMeasureSpec)

        // 设置控件的测量结果
        setMeasuredDimension(width, height)

        // 计算小圆点和背景圆角的半径
        thumbRadius = (height / 2f) - dpToPx(0)
        backgroundRadius = height / 2f

        // 根据当前状态设置小圆点的初始位置
        thumbPosition = if (isChecked) 1f else 0f // 初始状态
    }

    override fun onDraw(canvas: Canvas) {
        // 绘制轨道背景
        val trackLeft = paddingLeft.toFloat()
        val trackRight = width - paddingRight.toFloat()
        val trackCenterY = height / 2f
        val trackRadius = trackHeight / 2 // 轨道的圆角半径，和小圆点保持一致


        // 使用 RectF 定义轨道的矩形区域
        val trackRect = RectF(
            trackLeft,
            trackCenterY - trackRadius,
            trackRight,
            trackCenterY + trackRadius
        )

        // 轨道的绘制
        paint.color = if (isChecked) checkedColor else uncheckedColor
        paint.style = Paint.Style.FILL

        // 绘制轨道背景
        canvas.drawRoundRect(
            trackRect,
            trackRadius, // 圆角半径
            trackRadius,
            paint
        )

        // 小圆点的滑动范围
        val thumbMinX = trackLeft + trackRadius // 起点位置，与轨道的左圆角相切
        val thumbMaxX = trackRight - trackRadius // 终点位置，与轨道的右圆角相切
        val thumbX = thumbMinX + (thumbMaxX - thumbMinX) * thumbPosition // 动态计算小圆点位置

        // 绘制小圆点
        paint.color = thumbColor
        canvas.drawCircle(thumbX, trackCenterY, trackRadius - dpToPx(3), paint) // 圆点半径和轨道圆角一致
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_UP) {
            isChecked = !isChecked   // 松手时切换选中状态
        }
        return true  //消费触摸事件
    }

    /**
     * 启动小圆点位置的动画
     * @param toChecked 动画目标状态
     */
    private fun startThumbAnimation(toChecked: Boolean) {
        val start = thumbPosition // 动画起始位置
        val end = if (toChecked) 1f else 0f // 计算目标位置（范围为 0 到 1）

        animator?.cancel() // 如果有正在运行的动画，取消
        animator = ValueAnimator.ofFloat(start, end).apply {
            duration = 250L // 动画时长 250 毫秒
            addUpdateListener { animation ->
                thumbPosition = animation.animatedValue as Float
                invalidate() // 更新界面
            }
            start()
        }
    }

    /**
     * 设置控件状态
     * @param checked 是否选中
     * @param animate 是否启用动画
     */
    fun setChecked(checked: Boolean, animate: Boolean = true) {
        if (isChecked == checked) return // 状态未改变，直接返回

        if (animate) {
            isChecked = checked // 启用动画
        } else {
            isChecked = checked
            thumbPosition = if (checked) 1f else 0f // 立即更新位置
            invalidate()
        }
    }

    /**
     * 设置选中状态的背景颜色
     */
    fun setCheckedColor(@ColorInt color: Int) {
        checkedColor = color
        invalidate()
    }

    /**
     * 设置未选中状态的背景颜色
     */
    fun setUncheckedColor(@ColorInt color: Int) {
        uncheckedColor = color
        invalidate()
    }

    /**
     * 设置圆点的颜色
     */
    fun setThumbColor(@ColorInt color: Int) {
        thumbColor = color
        invalidate()
    }
}