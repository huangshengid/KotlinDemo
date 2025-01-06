package com.llw.myapplicationkotlin.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.desaysv.mvvm.base.viewmodel.BaseViewModel
import com.llw.myapplicationkotlin.R
import com.llw.myapplicationkotlin.ui.home.bean.HomeItem

/**
 * @Description : 描述
 * @Date        : 2025/1/2 16:01
 * @Author      : uids0505
 */
class HomeViewModel : BaseViewModel() {
    val listData = MutableLiveData<MutableList<HomeItem>>()

    fun loadListData() {
        listData.value = mutableListOf<HomeItem>().apply {
            add(HomeItem("时钟设置", R.drawable.ic_clock))
            add(HomeItem("语言设置", R.drawable.ic_clock))
            add(HomeItem("系统信息", R.drawable.ic_clock))
            add(HomeItem("声音设置", R.drawable.ic_clock))
            add(HomeItem("DSRC设置", R.drawable.ic_clock))
            add(HomeItem("还原出厂设置", R.drawable.ic_clock))
            add(HomeItem("共享汽车模式设置", R.drawable.ic_clock))
            add(HomeItem("振动设置", R.drawable.ic_clock))
            add(HomeItem("灵敏度设置", R.drawable.ic_clock))
        }
    }
}