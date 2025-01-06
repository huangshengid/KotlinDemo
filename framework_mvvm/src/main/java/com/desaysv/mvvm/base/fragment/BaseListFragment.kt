package com.desaysv.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.drake.brv.BindingAdapter

/**
 * @Description : 描述
 * @Date        : 2025/1/3 15:24
 * @Author      : uids0505
 */
abstract class BaseListFragment<DB : ViewBinding, VM : ViewModel,  T : Any> : BaseMvvmFragment<DB, VM>() {
    protected lateinit var recyclerView: RecyclerView
    protected lateinit var adapter: BindingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化 RecyclerView
        recyclerView = provideRecyclerView() // 使用新的方法名称
        adapter = createAdapter()
        recyclerView.layoutManager = createLayoutManager()

        // 加载数据
        observeData()
    }

    // 子类提供 RecyclerView 的实例
    abstract fun provideRecyclerView(): RecyclerView

    // 创建 BindingAdapter，子类需要传递布局和类型
    open fun createAdapter(): BindingAdapter {
        val adapter = BindingAdapter()
        registerItemTypes(adapter)
        return adapter
    }

    // 提供给子类实现，用于注册多种类型的 ViewType
    abstract fun registerItemTypes(adapter: BindingAdapter)

    // 默认使用 LinearLayoutManager，可以被子类重写
    open fun createLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(requireContext())
    }

    // 子类观察数据，并提交给 Adapter
    abstract fun observeData()

}