package com.llw.myapplicationkotlin.ui.system

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.desaysv.mvvm.base.fragment.ImageItem
import com.desaysv.mvvm.base.fragment.ItemPosition
import com.desaysv.mvvm.base.fragment.TextItem
import com.desaysv.mvvm.base.viewmodel.BaseViewModel
import com.llw.myapplicationkotlin.R

/**
 * @Description : 描述
 * @Date        : 2025/1/3 15:52
 * @Author      : uids0505
 */
class SystemInfoViewModel : BaseViewModel() {
    val listData = MutableLiveData<MutableList<ItemPosition>>()

//    init {
//        loadListData()
//    }

    fun loadListData() {
        listData.value = mutableListOf<ItemPosition>().apply {
            add(TextItem("时钟设置", R.drawable.ic_clock))
            add(TextItem("语言设置", R.drawable.ic_clock))
            add(TextItem("系统设置", R.drawable.ic_clock))
            add(TextItem("DSRC设置", R.drawable.ic_clock))
            add(TextItem("还原出厂设置", R.drawable.ic_clock))
            add(ImageItem(R.drawable.ic_sound))
            add(TextItem("共享汽车模式设置", R.drawable.ic_clock))
            add(TextItem("振动设置", R.drawable.ic_clock))
            add(ImageItem(R.drawable.ic_sound))
        }
    }

}