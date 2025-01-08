package com.llw.myapplicationkotlin.ui.factory

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.desaysv.mvvm.base.dialog.BaseDialog
import com.desaysv.mvvm.base.dialog.BaseDialogFragment
import com.desaysv.mvvm.ext.onClick
import com.desaysv.mvvm.utils.ViewUtils
import com.llw.myapplicationkotlin.databinding.DialogFactoryResetConfirmBinding

/**
 * @Description : 恢复出厂设置
 * @Date        : 2025/1/7 20:39
 * @Author      : uids0505
 */
class FactoryResetDialog {
    class Builder(activity: FragmentActivity) : BaseDialogFragment.Builder<Builder>(activity) {
        private var mBinding: DialogFactoryResetConfirmBinding =
            DialogFactoryResetConfirmBinding.inflate(LayoutInflater.from(activity))

        private var mOnConfirmCall: (() -> Unit)? = null

        init {
            initView()
        }

        private fun initView() {
            Log.d("FactoryResetDialog", "initView ${mBinding.root}")
            setContentView(mBinding.root)
            setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
            setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
            setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
            gravity = Gravity.BOTTOM
            ViewUtils.setClipViewCornerRadius(mBinding.root, 20)

            mBinding.btnCancel.onClick {
                Log.d("FactoryResetDialog", "cancel")
                dismiss()
                mOnConfirmCall?.invoke()
            }
            mBinding.btnConfirm.onClick {
                Log.d("FactoryResetDialog", "confirm")
                dismiss()
            }
        }

        fun setOnConfirmCall(onConfirmCall: () -> Unit): Builder {
            mOnConfirmCall = onConfirmCall
            return this
        }
    }
}