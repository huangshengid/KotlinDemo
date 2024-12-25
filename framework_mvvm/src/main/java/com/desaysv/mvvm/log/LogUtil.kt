package com.desaysv.mvvm.log

import android.util.Log


object LogUtil {
    private const val TAG = "LogUtil"

    private var mLogPath: String? = null

    @JvmOverloads
    fun v(message: String, throwable: Throwable? = null, tag: String? = null, saveLog: Boolean = false) {
        prepareLog(Log.VERBOSE, tag, message, throwable, saveLog)
    }

    @JvmOverloads
    fun d(message: String, throwable: Throwable? = null, tag: String? = null, saveLog: Boolean = false) {
        prepareLog(Log.DEBUG, tag, message, throwable, saveLog)
    }

    @JvmOverloads
    fun i(message: String, throwable: Throwable? = null, tag: String? = null, saveLog: Boolean = false) {
        prepareLog(Log.INFO, tag, message, throwable, saveLog)
    }

    @JvmOverloads
    fun w(message: String, throwable: Throwable? = null, tag: String? = null, saveLog: Boolean = false) {
        prepareLog(Log.WARN, tag, message, throwable, saveLog)
    }

    @JvmOverloads
    fun e(message: String, throwable: Throwable? = null, tag: String? = null, saveLog: Boolean = false) {
        prepareLog(Log.ERROR, tag, message, throwable, saveLog)
    }

    @JvmOverloads
    fun w(throwable: Throwable? = null, saveLog: Boolean = false) {
        prepareLog(Log.WARN, "", "", throwable, saveLog)
    }

    @JvmOverloads
    fun e(throwable: Throwable? = null, saveLog: Boolean = false) {
        prepareLog(Log.ERROR, "", "", throwable, saveLog)
    }

    private fun prepareLog(priority: Int, tag: String?, message: String, throwable: Throwable?, saveLog: Boolean) {
        val logTag = tag ?: TAG
        throwable?.let {
//            logger.logThrowable(logTag, it, message)
        } ?: run {
            // 按照日志级别打印，线上版本在warn及以上级别会打印到日志文件中
//            when (priority) {
//                Log.VERBOSE -> logger.v(logTag, message)
//                Log.DEBUG -> logger.d(logTag, message)
//                Log.INFO -> logger.i(logTag, message)
//                Log.WARN -> logger.w(logTag, message)
//                Log.ERROR -> logger.e(logTag, message)
//                else -> logger.v(logTag, message)
//            }
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

    /**
     * 将缓存中的日志刷新到文件里去
     */
    fun flushLog() {
//        logger.flushLog()
    }

    /**
     * 获取日志存放路径
     */
    fun getLogPath(): String? {
        return mLogPath
    }
}