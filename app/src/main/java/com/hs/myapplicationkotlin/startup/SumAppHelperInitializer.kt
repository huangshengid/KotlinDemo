package com.hs.myapplicationkotlin.startup

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.databinding.ktx.BuildConfig
import androidx.startup.Initializer
import com.desaysv.mvvm.helper.AppHelper

/**
 * @Description : 描述
 * @Date        : 2025/1/15 15:29
 * @Author      : uids0505
 */
class SumAppHelperInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        // 将 Context 转换为 Application
        Log.d("SumAppHelperInitializer", "create")
        val application = context.applicationContext as Application
        AppHelper.init(application, BuildConfig.DEBUG)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList() // 无依赖
    }
}