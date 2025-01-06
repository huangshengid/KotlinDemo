package com.llw.myapplicationkotlin.ui.system

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.llw.myapplicationkotlin.R

/**
 * @Description : 描述
 * @Date        : 2025/1/6 14:27
 * @Author      : uids0505
 */
class SystemInfoItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemViewType = parent.getChildViewHolder(view).itemViewType
        if (itemViewType == R.layout.item_text) {
            outRect.left = 20
            outRect.right = 5
            outRect.top = 5
            outRect.bottom = 30
        }
    }
}