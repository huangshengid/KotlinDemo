package com.llw.myapplicationkotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.TextView
import com.llw.myapplicationkotlin.R

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

    private val decreaseButton: View
    private val increaseButton: View
    private val seekBar: SeekBar
    private val indicator: TextView

    private val centerLinePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        strokeWidth = 4f // 中间竖条的宽度
    }

    var progress: Int
        get() = seekBar.progress
        set(value) {
            seekBar.progress = value
        }

    var onProgressChanged: ((Int) -> Unit)? = null

    init {
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_custom_slider, this, true)

        decreaseButton = findViewById(R.id.btn_decrease)
        increaseButton = findViewById(R.id.btn_increase)
        seekBar = findViewById(R.id.seekBar)
        indicator = findViewById(R.id.text_indicator)

        // 设置初始值
        seekBar.progress = 50
        updateIndicator(seekBar.progress)

        // 减少按钮
        decreaseButton.setOnClickListener {
            if (seekBar.progress > 0) {
                seekBar.progress -= 10
            }
        }

        // 增加按钮
        increaseButton.setOnClickListener {
            if (seekBar.progress < seekBar.max) {
                seekBar.progress += 10
            }
        }

        // 监听滑动条
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateIndicator(progress)
                onProgressChanged?.invoke(progress)
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
    private fun updateIndicator(progress: Int) {
        indicator.text = "$progress"
        val seekBarWidth = seekBar.width - seekBar.paddingStart - seekBar.paddingEnd
        val thumbOffset = seekBar.thumb.intrinsicWidth / 2
        val indicatorPosition = (progress.toFloat() / seekBar.max) * seekBarWidth - thumbOffset
        indicator.translationX = indicatorPosition
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        // 在滑动条中间绘制竖条
        val centerX = width / 2f
        val top = seekBar.top.toFloat()
        val bottom = seekBar.bottom.toFloat()
        canvas.drawLine(centerX, top, centerX, bottom, centerLinePaint)
    }
}