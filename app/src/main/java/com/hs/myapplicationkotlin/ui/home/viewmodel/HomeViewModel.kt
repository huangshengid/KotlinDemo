package com.hs.myapplicationkotlin.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.desaysv.mvvm.base.viewmodel.BaseViewModel
import com.hs.myapplicationkotlin.R
import com.hs.myapplicationkotlin.ui.clock.ClockFragment
import com.hs.myapplicationkotlin.ui.factory.FactoryFragment
import com.hs.myapplicationkotlin.ui.home.bean.HomeItem
import com.hs.myapplicationkotlin.ui.home.bean.TabItem
import com.hs.myapplicationkotlin.ui.language.LanguageFragment
import com.hs.myapplicationkotlin.ui.sound.SoundFragment
import com.hs.myapplicationkotlin.ui.system.SystemInfoFragment
import com.hs.myapplicationkotlin.ui.vibration.VibrationFragment

/**
 * @Description : 描述
 * @Date        : 2025/1/2 16:01
 * @Author      : uids0505
 */
class HomeViewModel : BaseViewModel() {
    val listData = MutableLiveData<MutableList<HomeItem>>()

    val tabData = MutableLiveData<MutableList<TabItem>>()

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

    fun loadTabData() {
        tabData.value = mutableListOf<TabItem>().apply {
            add(TabItem(0, "时钟设置", ClockFragment()))
            add(TabItem(1, "语言设置",LanguageFragment()))
            add(TabItem(2, "系统信息",SystemInfoFragment()))
            add(TabItem(3, "声音设置",SoundFragment()))
            add(TabItem(4, "还原出厂设置",FactoryFragment()))
            add(TabItem(5, "振动设置",VibrationFragment()))
        }
    }
}