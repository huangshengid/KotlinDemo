package com.llw.myapplicationkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
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
        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        // 检查是否有外部传递的参数
        handleDeepLinkIntent(intent, navController)
        // 不绑定 ActionBar
        navController.addOnDestinationChangedListener { _, destination, _ ->
            Log.d("NavDestination", "Navigated to ${destination.label}")
        }
    }

    // 配置返回按钮支持
    override fun onSupportNavigateUp(): Boolean {
        return mBinding.navHostFragment.findNavController()
            .navigateUp() || super.onSupportNavigateUp()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        // 处理新的 Intent（例如应用已经在前台，但又收到 Deep Link 调用）
        intent?.let {
            val navController = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
            handleDeepLinkIntent(it, navController)
        }
    }


    private fun handleDeepLinkIntent(intent: Intent, navController: NavController) {
        // 从 Intent 中获取 Deep Link 数据
        intent.data?.let { uri ->
            val selectedTabIndex = uri.getQueryParameter("tab")?.toIntOrNull() ?: 0 // 获取 tab 参数
            // 传递参数给 HomeFragment
            val bundle = Bundle().apply {
                putInt("selectedTabIndex", selectedTabIndex)
            }
            // 使用 NavController 导航到 HomeFragment，并携带参数
            navController.navigate(R.id.homeFragment, bundle)
        }
    }
}