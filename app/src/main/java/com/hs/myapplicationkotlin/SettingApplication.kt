package com.hs.myapplicationkotlin

import android.app.Application
import com.desaysv.mvvm.helper.AppHelper
import com.desaysv.mvvm.log.LogUtil
import com.desaysv.mvvm.manager.AppManager
import com.desaysv.mvvm.toast.TipsToast

/**
 * @Description : 描述
 * @Date        : 2024/12/26 12:12
 * @Author      : uids0505
 */
class SettingApplication : Application() {
    companion object {
        const val TAG = "SettingApplication"
    }

    override fun onCreate() {
        super.onCreate()
        LogUtil.d(TAG, "onCreate")
        AppHelper.init(this, BuildConfig.DEBUG)
        AppManager.init(this)
        TipsToast.init(this)
    }
}