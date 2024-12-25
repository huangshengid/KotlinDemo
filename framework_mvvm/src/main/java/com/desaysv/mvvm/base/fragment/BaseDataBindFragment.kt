package com.desaysv.mvvm.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.desaysv.mvvm.ext.saveAs
import com.desaysv.mvvm.ext.saveAsUnChecked
import java.lang.reflect.ParameterizedType

/**
 * @Description : dataBinding Fragment 基类
 * @Date        : 2024/12/25 11:52
 * @Author      : uids0505
 */
abstract class BaseDataBindFragment<DB : ViewBinding> : BaseFragment() {
    var mBinding: DB? = null
    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
//        mBinding = DataBindingUtil.inflate(inflater, getLayoutResId(), container, false)
        val type = javaClass.genericSuperclass
        val vbClass: Class<DB> = type!!.saveAs<ParameterizedType>().actualTypeArguments[0].saveAs()
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        mBinding = method.invoke(this, layoutInflater)!!.saveAsUnChecked()
        return mBinding!!.root
    }

    override fun getLayoutResId(): Int = 0

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}