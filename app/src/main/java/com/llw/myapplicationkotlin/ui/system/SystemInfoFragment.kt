package com.llw.myapplicationkotlin.ui.system

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.desaysv.mvvm.base.fragment.BaseToolbarFragment
import com.desaysv.mvvm.base.fragment.ImageItem
import com.desaysv.mvvm.base.fragment.TextItem
import com.drake.brv.utils.grid
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import com.llw.myapplicationkotlin.R
import com.llw.myapplicationkotlin.databinding.FragmentSystemInfoBinding

/**
 * @Description : 描述
 * @Date        : 2025/1/2 17:34
 * @Author      : uids0505
 */
class SystemInfoFragment : BaseMvvmFragment<FragmentSystemInfoBinding, SystemInfoViewModel>(){

//    override fun setupTitleBar() {
//        setTitle("系统信息")
//    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding?.rvSystem?.apply {
            grid(2)
            addItemDecoration(SystemInfoItemDecoration())
        }?.setup {
            addType<TextItem>(R.layout.item_text)
            addType<ImageItem>(R.layout.item_image)
            onBind {
                when (itemViewType) {
                    R.layout.item_text -> {
                        findView<TextView>(R.id.tvText).text = getModel<TextItem>().text
                        findView<ImageView>(R.id.iv_icon).setImageResource(getModel<TextItem>().imageId)
                    }
                    R.layout.item_image -> {
                        findView<ImageView>(R.id.imageView).setImageResource(getModel<ImageItem>().imageId)
                    }
                }
            }

            R.id.item_root.onClick {
                Log.d("llw", "position:$modelPosition")
                findNavController().navigate(R.id.soundFragment)
            }
        }

        mViewModel.listData.observe(viewLifecycleOwner) {
            mBinding?.rvSystem?.models = it
        }
    }

    override fun initData() {
        super.initData()
        mViewModel.loadListData()
    }

}