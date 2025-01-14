package com.hs.myapplicationkotlin.ui.vibration.viewmodel

import androidx.lifecycle.MutableLiveData
import com.desaysv.mvvm.base.viewmodel.BaseViewModel

/**
 * @Description : 描述
 * @Date        : 2025/1/6 16:40
 * @Author      : uids0505
 */
class VibrationViewModel : BaseViewModel() {
    var vibrationStatus = MutableLiveData<Boolean>()
    fun loadVibrationStatus() {
        launchBackground {
            //模拟耗时操作
//            sleep(30)
         vibrationStatus.postValue(false)
        }
    }




}