package com.hs.myapplicationkotlin.ui.vibration

import android.os.Bundle
import android.view.View
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.hs.myapplicationkotlin.databinding.FragmentVibrationBinding
import com.hs.myapplicationkotlin.ui.vibration.viewmodel.VibrationViewModel

/**
 * @Description : 震动fragment
 * @Date        : 2025/1/2 17:41
 * @Author      : uids0505
 */
class VibrationFragment : BaseMvvmFragment<FragmentVibrationBinding, VibrationViewModel>() {
    override fun initView(view: View, savedInstanceState: Bundle?) {
        mViewModel.vibrationStatus.observe(viewLifecycleOwner) {
            mBinding?.sbnVibration?.isChecked = it
        }
    }

    override fun initData() {
        mViewModel.loadVibrationStatus()
    }
}