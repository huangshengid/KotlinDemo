package com.desaysv.mvvm.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @Description : 描述
 * @Date        : 2024/12/25 20:54
 * @Author      : uids0505
 */
open class BaseViewModel : ViewModel() {
    private fun launchUi(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch { block() }

}