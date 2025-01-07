package com.llw.myapplicationkotlin.ui.factory

import android.os.Bundle
import android.view.View
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.desaysv.mvvm.base.fragment.BaseToolbarFragment
import com.llw.myapplicationkotlin.databinding.FragmentFactoryBinding
import com.llw.myapplicationkotlin.ui.factory.viewmodel.FactoryViewModel

/**
 * @Description : 描述
 * @Date        : 2025/1/2 17:42
 * @Author      : uids0505
 */
class FactoryFragment : BaseMvvmFragment<FragmentFactoryBinding, FactoryViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {

    }
}