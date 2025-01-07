package com.llw.myapplicationkotlin.ui.home.adapter

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @Description : 描述
 * @Date        : 2025/1/6 20:07
 * @Author      : uids0505
 */
class ViewPagerAdapter(activity: FragmentActivity, var fragments: SparseArray<Fragment>) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return fragments.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

    fun setData(fragments: SparseArray<Fragment>) {
        this.fragments = fragments
    }
}