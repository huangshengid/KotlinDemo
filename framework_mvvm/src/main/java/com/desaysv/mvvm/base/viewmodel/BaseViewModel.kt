package com.desaysv.mvvm.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @Description : 描述
 * @Date        : 2024/12/25 20:54
 * @Author      : uids0505
 */
open class BaseViewModel : ViewModel() {
    // 在后台线程执行任务的方法
    // 该方法通过 viewModelScope 启动一个协程，执行后台任务
    private fun launchBackground(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {  // 使用 IO 线程池执行后台任务
            block()
        }

    // 在主线程执行任务的方法
    // 该方法确保传递的任务会在主线程执行
    private fun launchMainThread(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(Dispatchers.Main) {  // 使用 Main 线程池执行任务
            block()
        }
}