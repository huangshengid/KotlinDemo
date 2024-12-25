package com.desaysv.mvvm.ext

/**
 * @Description : 描述
 * @Date        : 2024/12/24 19:57
 * @Author      : uids0505
 */
inline fun <reified T> Any.saveAs() : T{
    return this as T
}

@Suppress("UNCHECKED_CAST")
fun <T> Any.saveAsUnChecked() : T{
    return this as T
}