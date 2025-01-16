package com.desaysv.mvvm.log

import android.util.Log


object LogUtil {
    private const val TAG = "LogUtil"

    fun v(tag: String, message: String) {
        prepareLog(Log.VERBOSE, tag, message)
    }

    fun d(tag: String, message: String) {
        prepareLog(Log.DEBUG, tag, message)
    }

    fun i(tag: String, message: String) {
        prepareLog(Log.INFO, tag, message)
    }

    fun w(tag: String, message: String) {
        prepareLog(Log.WARN, tag, message)
    }

    fun e(tag: String, message: String) {
        prepareLog(Log.ERROR, tag, message)
    }

    private fun prepareLog(
        priority: Int,
        tag: String?,
        message: String,
    ) {
        val logTag = tag ?: TAG

        when (priority) {
            Log.VERBOSE -> Log.v(logTag, message)
            Log.DEBUG -> Log.d(logTag, message)
            Log.INFO -> Log.i(logTag, message)
            Log.WARN -> Log.w(logTag, message)
            Log.ERROR -> Log.e(logTag, message)
            else -> Log.v(logTag, message)
        }
    }
}