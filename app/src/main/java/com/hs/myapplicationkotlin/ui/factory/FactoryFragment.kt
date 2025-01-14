package com.hs.myapplicationkotlin.ui.factory

import android.os.Bundle
import android.util.Log
import android.view.View
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.desaysv.mvvm.ext.onClick
import com.desaysv.mvvm.toast.TipsToast
import com.hs.myapplicationkotlin.databinding.FragmentFactoryBinding
import com.hs.myapplicationkotlin.ui.factory.viewmodel.FactoryViewModel
import com.hs.myapplicationkotlin.widget.SwitchButton

/**
 * @Description : 恢复出厂设置
 * @Date        : 2025/1/2 17:42
 * @Author      : uids0505
 */
class FactoryFragment : BaseMvvmFragment<FragmentFactoryBinding, FactoryViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        (mBinding?.sbnDemo as? SwitchButton)?.onCheckedChangeListener = {
            if (it) {
                TipsToast.showTips("开启")
            } else {
                TipsToast.showTips("关闭")
            }
        }

        mBinding?.customSlider?.setRange(2, 12, 6,1)
        mBinding?.customSlider?.onProgressChanged = {
            Log.d(TAG, "initView: 进度变化   $it")
        }


        mBinding?.btDialog?.onClick {
            FactoryResetDialog.Builder(requireActivity()).setOnConfirmCall {
                Log.d(TAG, "initView: 点击了确定")
            }.show()
//            TipsToast.showTips("点击了确定")




            MessageDialog.Builder(requireActivity()).setTitle("温馨提示")
                .setMessage("恢复出厂设置，将恢复为默认设置，是否继续？")
                .setConfirm("确定")
//                .setConfirmTxtColor(activity?.resources.getColorFromResource(R.color.color_0165b8))
                .setCancel("取消")
                .setonCancelListener {
                    it?.dismiss()
                }
                .setonConfirmListener {
                    Log.d(TAG, "initView: 点击了确定")
                    it?.dismiss()
                }.create().show()
        }
    }
}