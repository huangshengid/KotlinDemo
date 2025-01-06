package com.llw.myapplicationkotlin.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.desaysv.mvvm.base.fragment.BaseToolbarFragment
import com.desaysv.mvvm.log.LogUtil
import com.drake.brv.utils.grid
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import com.llw.myapplicationkotlin.R
import com.llw.myapplicationkotlin.databinding.FragmentHomeBinding
import com.llw.myapplicationkotlin.databinding.ItemHomeBinding
import com.llw.myapplicationkotlin.ui.home.bean.HomeItem
import com.llw.myapplicationkotlin.ui.home.viewmodel.HomeViewModel
/**
 * @Description : 首页
 * @Date        : 2025/1/2 16:00
 * @Author      : uids0505
 */
class HomeFragment : BaseToolbarFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun initView(view: View, savedInstanceState: Bundle?) {
        LogUtil.i(tag = "HomeFragment", message = "initView")
        mBinding?.rvHome?.apply {
            grid(5)
            addItemDecoration(HomeItemDecoration())
        }?.setup {
            addType<HomeItem>(R.layout.item_home)
            onBind {
                val item = getModel<HomeItem>()
                val binding = getBinding<ItemHomeBinding>()
                binding.apply {
                    ivIcon.setImageResource(item.imageId)
                    tvTitle.text = item.title
                    root.setOnClickListener {
                        when (item.title) {
                            "时钟设置" -> { findNavController().navigate(R.id.clockFragment) }
                            "语言设置" -> { findNavController().navigate(R.id.languageFragment) }
                            "系统信息" -> { findNavController().navigate(R.id.systemInfoFragment) }
                            "还原出厂设置" -> { findNavController().navigate(R.id.factoryFragment) }
                            "振动设置" -> { findNavController().navigate(R.id.vibrationFragment) }
                            "声音设置" -> { findNavController().navigate(R.id.soundFragment) }
                        }
                    }
                }
            }
        }
        mViewModel.listData.observe(viewLifecycleOwner) {
            mBinding?.rvHome?.models = it
        }
    }

    override fun initData() {
        mViewModel.loadListData()
    }

    override fun setupTitleBar() {
        setTitle("首页")
        // 设置右侧扩展布局（如右上角按钮）
//        setRightLayout(R.layout.toolbar_right_menu)
    }
}