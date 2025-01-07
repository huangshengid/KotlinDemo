package com.desaysv.mvvm.base.fragment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @Description : DataBinding和ViewModel基类
 * @Date        : 2024/12/25 11:52
 * @Author      : uids0505
 */
abstract class BaseMvvmFragment<DB : ViewBinding, VM : ViewModel> :BaseDataBindFragment<DB>() {
    lateinit var mViewModel: VM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // 隐藏系统的 ActionBar
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
        initViewModel()
        // 包裹子类布局的根容器
        super.onViewCreated(view, savedInstanceState)
    }

    open fun initViewModel() {
        val argument = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        mViewModel = ViewModelProvider(this).get(argument[1] as Class<VM>)
    }
}