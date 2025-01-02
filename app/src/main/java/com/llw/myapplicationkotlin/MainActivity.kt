package com.llw.myapplicationkotlin

import android.os.Bundle
import com.desaysv.mvvm.base.activity.BaseMvvmActivity
import com.desaysv.mvvm.log.LogUtil
import com.llw.myapplicationkotlin.databinding.ActivityMainBinding

class MainActivity : BaseMvvmActivity<ActivityMainBinding, MainViewModel>() {
    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        LogUtil.d("initView")
        supportActionBar?.hide()
        mBinding.tvHello.setOnClickListener {
            LogUtil.d("click")
        }
    }
}