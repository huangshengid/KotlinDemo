package com.hs.myapplicationkotlin.ui.factory

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import com.desaysv.mvvm.base.dialog.BaseDialog
import com.desaysv.mvvm.base.dialog.BaseDialogFragment
import com.desaysv.mvvm.ext.onClick
import com.desaysv.mvvm.manager.AppManager
import com.desaysv.mvvm.utils.ViewUtils
import com.hs.myapplicationkotlin.databinding.DialogFactoryResetConfirmBinding

/**
 * @Description : 恢复出厂设置
 * @Date        : 2025/1/13 20:39
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
            Log.d("FactoryResetDialog", "initView ${mBinding.root} + ${(AppManager.getScreenWidthPx() * 0.8).toInt()} + , ${(AppManager.getScreenHeightPx() * 0.8).toInt()}")
            setContentView(mBinding.root)
            setWidth(320)
            setHeight(300)
            setAnimStyle(BaseDialog.AnimStyle.BOTTOM)
            gravity = Gravity.CENTER
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

        /**
         * 为什么返回 Builder？
         * 方法返回 Builder 本身，使得这个方法支持链式调用
         */
        fun setOnConfirmCall(onConfirmCall: () -> Unit): Builder {
            mOnConfirmCall = onConfirmCall
            return this
        }
    }
}