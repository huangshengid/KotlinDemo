package com.llw.myapplicationkotlin

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.desaysv.mvvm.base.activity.BaseMvvmActivity
import com.desaysv.mvvm.log.LogUtil
import com.llw.myapplicationkotlin.databinding.ActivityMainBinding

class MainActivity : BaseMvvmActivity<ActivityMainBinding, MainViewModel>() {

    override fun initView(savedInstanceState: Bundle?) {
        LogUtil.d("initView")
        // 获取 NavController
        val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        setupActionBarWithNavController(navController)
    }

    // 配置返回按钮支持
    override fun onSupportNavigateUp(): Boolean {
        return mBinding.navHostFragment.findNavController().navigateUp() || super.onSupportNavigateUp()
    }
}