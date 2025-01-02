package com.llw.myapplicationkotlin.ui.sound

import android.os.Bundle
import android.view.View
import com.desaysv.mvvm.base.fragment.BaseMvvmFragment
import com.desaysv.mvvm.base.fragment.BaseToolbarFragment
import com.desaysv.mvvm.log.LogUtil
import com.llw.myapplicationkotlin.databinding.FragmentSoundBinding
import com.llw.myapplicationkotlin.ui.sound.viewmodel.SoundViewModel

/**
 * @Description : 描述
 * @Date        : 2025/1/2 17:32
 * @Author      : uids0505
 */
class SoundFragment : BaseToolbarFragment<FragmentSoundBinding, SoundViewModel>(){

    override fun initView(view: View, savedInstanceState: Bundle?) {
        LogUtil.d("initView")
    }

    override fun setupTitleBar() {
        setTitle("声音")
    }
}