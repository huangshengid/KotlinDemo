package com.hs.myapplicationkotlin.ui.vibration

import androidx.lifecycle.ViewModelStore
import com.desaysv.mvvm.base.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Thread.sleep

/**
 * @Description : 描述
 * @Date        : 2025/1/14 21:19
 * @Author      : uids0505
 */
class VibrationRepository : BaseRepository() {

    suspend fun getVibrationStatus(): Boolean {
        return withIo {
            // 数据库或网络操作
            sleep(1000)
            true
        }
    }
}