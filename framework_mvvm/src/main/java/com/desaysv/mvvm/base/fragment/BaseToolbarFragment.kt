package com.desaysv.mvvm.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.desaysv.mvvm.R

/**
 * @Description : 提供带标题栏的基类Fragment，并且支持自定义布局
 * @Date        : 2024/12/25 13:45
 * @Author      : uids0505
 */
abstract class BaseToolbarFragment<DB : ViewBinding, VM : ViewModel> : BaseMvvmFragment<DB, VM>() {
    private lateinit var containerLayout: LinearLayout // 根容器，包含标题栏和子类布局
    private var titleTextView: TextView? = null
    private var rightContainer: FrameLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val originalView = super.onCreateView(inflater, container, savedInstanceState)
        return createRootView(originalView!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTitleBar()
    }

    /**
     * 创建根布局，包含标题栏 + 子类布局
     */
    private fun createRootView(view: View): View {
        // 外层布局容器
        containerLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // 添加自定义标题栏
        val toolbar = layoutInflater.inflate(R.layout.toolbar, containerLayout, false)
        containerLayout.addView(toolbar)

        // 将 Fragment 原始内容布局嵌套到内容容器中
        val contentContainer = FrameLayout(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // 移除原始 view 的父布局，重新添加到 contentContainer
        val parent = view.parent as? ViewGroup
        parent?.removeView(view)
        contentContainer.addView(view)

        // 动态调整内容容器的顶部内边距，避免被标题栏覆盖
        contentContainer.setPadding(0, toolbar.measuredHeight, 0, 0)

        containerLayout.addView(contentContainer)

        // 初始化标题栏中的控件
        titleTextView = toolbar.findViewById(R.id.toolbar_title)
        rightContainer = toolbar.findViewById(R.id.toolbar_right_container)
        val backButton = toolbar.findViewById<ImageView>(R.id.btn_back)

        backButton?.apply {
            visibility = if (hasBackButton()) View.VISIBLE else View.GONE
            setOnClickListener {
                // 使用 NavController 处理返回逻辑
                if (!findNavController().popBackStack()) {
                    // 如果 NavController 的返回栈为空，则退出 Activity
                    activity?.onBackPressedDispatcher?.onBackPressed()
                }
            }
        }

        return containerLayout
    }

    protected fun setTitle(title: String) {
        titleTextView?.text = title
    }

    /**
     * 子类可以调用该方法设置右侧扩展布局
     */
    protected fun setRightLayout(layoutResId: Int?) {
        rightContainer?.removeAllViews()
        layoutResId?.let {
            val inflater = LayoutInflater.from(rightContainer?.context)
            val view = inflater.inflate(it, rightContainer, false)
            rightContainer?.addView(view)
        }
    }

    /**
     * 是否显示返回按钮，子类可以重写决定
     */
    open fun hasBackButton(): Boolean = true

    /**
     * 初始化标题栏（子类可重写此方法自定义逻辑）
     */
    open fun setupTitleBar() {
        setTitle("默认标题") // 子类可以通过 `setTitle` 自定义标题
    }
}