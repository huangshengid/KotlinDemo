package com.llw.myapplicationkotlin

import android.app.Application
import com.desaysv.mvvm.helper.SumAppHelper
import com.desaysv.mvvm.manager.AppManager
import com.desaysv.mvvm.toast.TipsToast

/**
 * @Description : 描述
 * @Date        : 2024/12/26 12:12
 * @Author      : uids0505
 */
class SettingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SumAppHelper.init(this, BuildConfig.DEBUG)
        AppManager.init(this)
        TipsToast.init(this)
    }
}