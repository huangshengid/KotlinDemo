package com.hs.myapplicationkotlin.ui.sound

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.desaysv.mvvm.ext.onClick
import com.desaysv.mvvm.log.LogUtil
import com.hs.myapplicationkotlin.R
import com.hs.myapplicationkotlin.databinding.FragmentSoundBinding
import com.hs.myapplicationkotlin.ui.sound.viewmodel.SoundViewModel

/**
 * @Description : 描述
 * @Date        : 2025/1/2 17:32
 * @Author      : uids0505
 */
class SoundFragment : BaseMvvmFragment<FragmentSoundBinding, SoundViewModel>(){
    private lateinit var navController: NavController
    override fun initView(view: View, savedInstanceState: Bundle?) {
        LogUtil.d("SoundFragment","initView")
        // 获取 NavController
        navController = childFragmentManager.findFragmentById(R.id.nav_sound)
            ?.findNavController() ?: throw IllegalStateException("NavController not found")
        mBinding?.btnEffectSetting?.onClick {
            navController.navigate(R.id.soundEffectFragment)
        }

        mBinding?.btnVolume?.onClick {
            navController.navigate(R.id.volumeFragment)
        }
    }

//    override fun setupTitleBar() {
//        setTitle("声音")
//    }
}