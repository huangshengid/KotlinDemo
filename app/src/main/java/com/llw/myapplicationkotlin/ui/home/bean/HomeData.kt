package com.llw.myapplicationkotlin.ui.home.bean

import androidx.fragment.app.Fragment
import com.desaysv.mvvm.base.fragment.ItemPosition

/**
 * @Description : 描述
 * @Date        : 2025/1/6 15:52
 * @Author      : uids0505
 */

data class HomeItem(val title: String, val imageId: Int, override var itemPosition: Int = 0) :
    ItemPosition

data class TabItem(val id: Int, val title: String,val fragment: Fragment)