package com.llw.myapplicationkotlin.ui.factory

import android.os.Bundle
import android.util.Log
import android.view.View
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.desaysv.mvvm.base.fragment.BaseToolbarFragment
import com.desaysv.mvvm.ext.onClick
import com.llw.myapplicationkotlin.databinding.FragmentFactoryBinding
import com.llw.myapplicationkotlin.ui.factory.viewmodel.FactoryViewModel

/**
 * @Description : 恢复出厂设置
 * @Date        : 2025/1/2 17:42
 * @Author      : uids0505
 */
class FactoryFragment : BaseMvvmFragment<FragmentFactoryBinding, FactoryViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        mBinding?.btDialog?.onClick {
            FactoryResetDialog.Builder(requireActivity()).setOnConfirmCall {
                Log.d(TAG, "initView: 点击了确定")
            }.show()
//            MessageDialog.Builder(requireActivity()).setTitle("温馨提示")
//                .setMessage("是否清除搜索历史记录？")
//                .setConfirm("确定")
////                .setConfirmTxtColor(activity?.resources.getColorFromResource(R.color.color_0165b8))
//                .setCancel("取消")
//                .setonCancelListener {
//                    it?.dismiss()
//                }
//                .setonConfirmListener {
////                    SearchManager.clearSearchHistory()
//                    Log.d(TAG, "initView: 点击了确定")
//                    it?.dismiss()
//                }.create().show()
        }
    }
}