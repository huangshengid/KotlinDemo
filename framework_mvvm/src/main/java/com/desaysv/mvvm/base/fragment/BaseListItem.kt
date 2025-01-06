package com.desaysv.mvvm.base.fragment

/**
 * @Description : 描述
 * @Date        : 2025/1/3 15:31
 * @Author      : uids0505
 */

// 定义多种类型的数据
data class TextItem(val text: String, val imageId: Int, override var itemPosition: Int = 0) :
    ItemPosition

data class ImageItem(val imageId: Int, override var itemPosition: Int = 0) : ItemPosition
