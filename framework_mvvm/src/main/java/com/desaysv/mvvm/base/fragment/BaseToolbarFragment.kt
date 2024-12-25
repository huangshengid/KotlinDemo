package com.desaysv.mvvm.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.desaysv.mvvm.R

/**
 * @Description : 描述
 * @Date        : 2024/12/25 13:45
 * @Author      : uids0505
 */
abstract class BaseToolbarFragment<DB : ViewBinding, VM : ViewModel> : BaseMvvmFragment<DB, VM>() {
    lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 加载基础布局
        val rootView = inflater.inflate(R.layout.layout_base_toolbar, container, false)

        // 初始化 Toolbar
        toolbar = rootView.findViewById(R.id.toolbar)
        setupToolbar()

        // 加载子类提供的内容布局到 content_container
        val contentContainer = rootView.findViewById<FrameLayout>(R.id.content_container)
        inflater.inflate(getContentLayoutResId(), contentContainer, true)

        return rootView
    }

    /**
     * 初始化 Toolbar
     */
    open fun setupToolbar() {
        toolbar = requireView().findViewById(R.id.toolbar) // 布局中必须包含 Toolbar
        toolbar.title = getTitle()
//        toolbar.setNavigationIcon(R.drawable.ic_back) // 设置返回图标
        toolbar.setNavigationOnClickListener { requireActivity().onBackPressedDispatcher.onBackPressed() }

        // 如果需要右侧自定义布局，调用子类的方法
        addCustomViewToToolbar()
    }

    /**
     * 子类必须提供内容布局的资源 ID
     */
    abstract fun getContentLayoutResId(): Int

    /**
     * 子类可通过重写此方法自定义 Toolbar 标题
     */
    open fun getTitle(): String = ""

    /**
     * 子类可通过重写此方法自定义 Toolbar 的右侧布局
     */
    open fun addCustomViewToToolbar() {
        // 默认不添加任何自定义布局
    }
}