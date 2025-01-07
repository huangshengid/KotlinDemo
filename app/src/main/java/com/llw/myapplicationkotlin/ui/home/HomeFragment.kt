package com.llw.myapplicationkotlin.ui.home

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.desaysv.mvvm.base.fragment.BaseToolbarFragment
import com.desaysv.mvvm.log.LogUtil
import com.drake.brv.utils.grid
import com.drake.brv.utils.models
import com.drake.brv.utils.setup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.llw.myapplicationkotlin.R
import com.llw.myapplicationkotlin.databinding.FragmentHomeBinding
import com.llw.myapplicationkotlin.databinding.ItemHomeBinding
import com.llw.myapplicationkotlin.ui.home.adapter.ViewPagerAdapter
import com.llw.myapplicationkotlin.ui.home.bean.HomeItem
import com.llw.myapplicationkotlin.ui.home.bean.TabItem
import com.llw.myapplicationkotlin.ui.home.viewmodel.HomeViewModel
/**
 * @Description : 首页
 * @Date        : 2025/1/2 16:00
 * @Author      : uids0505
 */
class HomeFragment : BaseMvvmFragment<FragmentHomeBinding, HomeViewModel>() {
    private var mFragmentAdapter: ViewPagerAdapter? = null
    private var mArrayTabFragments = SparseArray<Fragment>()
    private var mTabList : MutableList<TabItem>? = mutableListOf<TabItem>()
    private var mTabLayoutMediator: TabLayoutMediator? = null
    private var selectedTabIndex: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    override fun initView(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            selectedTabIndex = it.getInt("selectedTabIndex", 0) // 默认选中第 0 个 Tab
        }
        LogUtil.i(tag = "HomeFragment", message = "initView selectedTabIndex:$selectedTabIndex")
//        mBinding?.rvHome?.apply {
//            grid(5)
//            addItemDecoration(HomeItemDecoration())
//        }?.setup {
//            addType<HomeItem>(R.layout.item_home)
//            onBind {
//                val item = getModel<HomeItem>()
//                val binding = getBinding<ItemHomeBinding>()
//                binding.apply {
//                    ivIcon.setImageResource(item.imageId)
//                    tvTitle.text = item.title
//                    root.setOnClickListener {
//                        when (item.title) {
//                            "时钟设置" -> { findNavController().navigate(R.id.clockFragment) }
//                            "语言设置" -> { findNavController().navigate(R.id.languageFragment) }
//                            "系统信息" -> { findNavController().navigate(R.id.systemInfoFragment) }
//                            "还原出厂设置" -> { findNavController().navigate(R.id.factoryFragment) }
//                            "振动设置" -> { findNavController().navigate(R.id.vibrationFragment) }
//                            "声音设置" -> { findNavController().navigate(R.id.soundFragment) }
//                        }
//                    }
//                }
//            }
//        }
//        mViewModel.listData.observe(viewLifecycleOwner) {
//            mBinding?.rvHome?.models = it
//        }

        //tabLayout
        initTab()

        mViewModel.tabData.observe(viewLifecycleOwner) {
            Log.d("HomeFragment", "tabData.observe")
            it.forEachIndexed { index, tabItem ->
                mArrayTabFragments.append(index, tabItem.fragment)
                mTabList?.add(tabItem)
            }
            mFragmentAdapter?.setData(mArrayTabFragments)
            mFragmentAdapter?.notifyDataSetChanged()
            mBinding?.vpContent?.setCurrentItem(selectedTabIndex, false) // 禁用切换动画
        }
    }

    private fun initTab() {
        Log.d("HomeFragment", "initTab")
        activity?.let {
            mFragmentAdapter = ViewPagerAdapter(it, mArrayTabFragments)
        }

        mBinding?.let { it ->
            it.vpContent.adapter = mFragmentAdapter

            //禁止滑动
            it.vpContent.isUserInputEnabled = false
            //禁用viewPager动画
            it.tlHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        mBinding?.vpContent?.setCurrentItem(it.position, false) // 禁用动画切换
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
            //禁用预加载
            //需要注意是FragmentStateAdapter不会一直保持Fragment实例，在被destroy后，需要做好Fragment重建后回复数据的准备，这点可以结合ViewModel来进行配合使用。
//            it.vpContent.offscreenPageLimit = mArrayTabFragments.size()
            mTabLayoutMediator = TabLayoutMediator(it.tlHome, it.vpContent) { tab, position ->
                tab.customView = createCustomTabView(mTabList?.get(position)?.title ?: "Tab $position")
            }
            //tabLayout和viewPager2关联起来
            mTabLayoutMediator?.attach()
            if (mTabList?.size!! > selectedTabIndex){
                mBinding?.vpContent?.setCurrentItem(selectedTabIndex, false) // 禁用切换动画
                selectedTabIndex = 0
            }

            it.tlHome.addOnTabSelectedListener(tabSelectedCall)

        }

    }

    override fun initData() {
//        mViewModel.loadListData()
        mViewModel.loadTabData()
    }

//    override fun setupTitleBar() {
//        setTitle("首页")
//        // 设置右侧扩展布局（如右上角按钮）
////        setRightLayout(R.layout.toolbar_right_menu)
//    }

    /**
     * tab选择回调
     */
    private val tabSelectedCall = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            setTabSelectedStyle(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            setTabUnselectedStyle(tab)
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
        }
    }

    private fun createCustomTabView(title: String): View {
        val customView = LayoutInflater.from(requireContext()).inflate(R.layout.custom_tab_item, null)
        val tabTextView = customView.findViewById<TextView>(R.id.tabTextView)
        tabTextView.text = title
        return customView
    }

    private fun setTabSelectedStyle(tab: TabLayout.Tab?) {
        val tabTextView = tab?.customView?.findViewById<TextView>(R.id.tabTextView)
        tabTextView?.apply {
            typeface = Typeface.DEFAULT_BOLD
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 32f)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        }
    }

    private fun setTabUnselectedStyle(tab: TabLayout.Tab?) {
        val tabTextView = tab?.customView?.findViewById<TextView>(R.id.tabTextView)
        tabTextView?.apply {
            typeface = Typeface.DEFAULT
            setTextSize(TypedValue.COMPLEX_UNIT_SP, 28f)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.cardview_dark_background))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mTabLayoutMediator?.detach()
    }
}