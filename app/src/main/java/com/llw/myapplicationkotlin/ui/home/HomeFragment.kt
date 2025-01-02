package com.llw.myapplicationkotlin.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.desaysv.mvvm.base.fragment.BaseToolbarFragment
import com.desaysv.mvvm.log.LogUtil
import com.llw.myapplicationkotlin.R
import com.llw.myapplicationkotlin.databinding.FragmentHomeBinding
import com.llw.myapplicationkotlin.ui.home.viewmodel.HomeViewModel

/**
 * @Description : 描述
 * @Date        : 2025/1/2 16:00
 * @Author      : uids0505
 */
class HomeFragment : BaseToolbarFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun initView(view: View, savedInstanceState: Bundle?) {
        LogUtil.i(tag = "HomeFragment", message = "initView")
        // 设置自定义标题
//        (activity as AppCompatActivity).supportActionBar?.title = "自定义首页标题"
        mBinding?.btDetail?.setOnClickListener {
            // 使用 Navigation 组件进行跳转
            findNavController().navigate(R.id.soundFragment)
        }
    }

    override fun setupTitleBar() {
        setTitle("首页")
        // 设置右侧扩展布局（如右上角按钮）
//        setRightLayout(R.layout.toolbar_right_menu)
    }
}