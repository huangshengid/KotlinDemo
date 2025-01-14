package com.hs.myapplicationkotlin.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import com.hs.myapplicationkotlin.R

/**
 * @Description : 描述
 * @Date        : 2025/1/9 17:18
 * @Author      : uids0505
 */
class CustomSliderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    // 控件定义
    private val decreaseButton: View
    private val increaseButton: View
    private val seekBar: SeekBar
//    private val indicator: TextView

    // 自定义的最大值、最小值和默认值
    var minValue: Int = 0
    var maxValue: Int = 100
    var defaultValue: Int = 50
    var stepValue: Int = 1  // 默认步长是 1

    private val centerLinePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        strokeWidth = 4f // 中间竖条的宽度
    }

    var mProgress: Int
        get() = seekBar.progress + minValue  // 显示实际进度
        set(value) {
            // 设置进度时确保在 min 和 max 之间
            seekBar.progress = (value - minValue).coerceIn(0, maxValue - minValue)
//            updateIndicator(seekBar.progress)
        }


    var onProgressChanged: ((Int) -> Unit)? = null

    init {
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_custom_slider, this, true)

        decreaseButton = findViewById(R.id.btn_decrease)
        increaseButton = findViewById(R.id.btn_increase)
        seekBar = findViewById(R.id.seekBar)
//        indicator = findViewById(R.id.text_indicator)

        // 从 XML 中读取属性
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomSliderView, defStyleAttr, 0)
            minValue = typedArray.getInt(R.styleable.CustomSliderView_minValue, 0)
            maxValue = typedArray.getInt(R.styleable.CustomSliderView_maxValue, 100)
            defaultValue = typedArray.getInt(R.styleable.CustomSliderView_defaultValue, 50)
            stepValue = typedArray.getInt(R.styleable.CustomSliderView_stepValue, 1)  // 从 XML 中读取步长
            typedArray.recycle()
        }


        // 设置 SeekBar 的最大值、最小值和默认值
        seekBar.max = maxValue - minValue
        seekBar.progress = defaultValue - minValue
//        updateIndicator(seekBar.progress)


        // 减少按钮
        decreaseButton.setOnClickListener {
            // 按-按钮时减少 progress
            decreaseProgress()
        }

        // 增加按钮
        increaseButton.setOnClickListener {
            // 按+按钮时增加 progress
            increaseProgress()
        }

        // 监听滑动条
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 更新指示器和外部监听器
                mProgress = progress + minValue  // 将进度值映射到实际的范围
//                updateIndicator( mProgress)  // 将进度值映射到实际的范围
                onProgressChanged?.invoke(mProgress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // 可选：滑动开始
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // 可选：滑动停止
            }
        })
    }

    // 更新指示器
//    private fun updateIndicator(progress: Int) {
//        indicator.text = "$progress"
//        val seekBarWidth = seekBar.width - seekBar.paddingStart - seekBar.paddingEnd
//        val thumbOffset = seekBar.thumb.intrinsicWidth / 2
//        val indicatorPosition = ((progress - minValue).toFloat() / (maxValue - minValue)) * seekBarWidth - thumbOffset
//        indicator.translationX = indicatorPosition
//    }

    // 设置最小值、最大值、默认值和步长
    fun setRange(min: Int, max: Int, default: Int, step: Int) {
        minValue = min
        maxValue = max
        defaultValue = default
        stepValue = step
        seekBar.max = maxValue - minValue
        mProgress = defaultValue
    }

    // 减少进度
    private fun decreaseProgress() {
        if (mProgress > minValue) {
            mProgress -= stepValue  // 每次减去步长
        }
    }

    // 增加进度
    private fun increaseProgress() {
        if (mProgress < maxValue) {
            mProgress += stepValue  // 每次加上步长
        }
    }
}