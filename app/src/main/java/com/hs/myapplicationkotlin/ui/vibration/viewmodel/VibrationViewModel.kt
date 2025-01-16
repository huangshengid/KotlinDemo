package com.hs.myapplicationkotlin.ui.vibration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.desaysv.mvvm.base.viewmodel.BaseViewModel
import com.hs.myapplicationkotlin.ui.vibration.VibrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @Description : 描述
 * @Date        : 2025/1/6 16:40
 * @Author      : uids0505
 */
class VibrationViewModel : BaseViewModel() {
    var vibrationStatus = MutableLiveData<Boolean>()
    private val repository : VibrationRepository by lazy{
        VibrationRepository()
    }
    fun loadVibrationStatus() {
        launchMainThread {
            val vibrationStatus1 = repository.getVibrationStatus()
            vibrationStatus.value = vibrationStatus1
        }
    }
}