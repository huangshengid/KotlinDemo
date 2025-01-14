package com.hs.myapplicationkotlin.ui.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.hs.myapplicationkotlin.R

/**
 * @Description : 描述
 * @Date        : 2025/1/6 14:27
 * @Author      : uids0505
 */
class HomeItemDecoration : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val itemViewType = parent.getChildViewHolder(view).itemViewType
        if (itemViewType == R.layout.item_home) {
            outRect.left = 20
            outRect.right = 5
            outRect.top = 5
            outRect.bottom = 30
        }
    }
}