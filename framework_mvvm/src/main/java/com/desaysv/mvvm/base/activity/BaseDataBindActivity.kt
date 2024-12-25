package com.desaysv.mvvm.base.activity

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.desaysv.mvvm.ext.saveAs
import com.desaysv.mvvm.ext.saveAsUnChecked
import java.lang.reflect.ParameterizedType

/**
 * @Description : dataBinding Activity基类
 * 过泛型和反射，自动初始化与 Activity 对应的 ViewBinding 对象，省去了手动调用 inflate() 和 findViewById() 的麻烦
 * @Date        : 2024/12/24 19:53
 * @Author      : uids0505
 */
abstract class BaseDataBindActivity<DB : ViewBinding> : BaseActivity() {
    lateinit var  mBinding : DB

    override fun setContentLayout() {
//        mBinding = DataBindingUtil.setContentView(this, getLayoutResId())
        val type = javaClass.genericSuperclass
        val vbClass: Class<DB> = type!!.saveAs<ParameterizedType>().actualTypeArguments[0].saveAs()
        val method = vbClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
        mBinding = method.invoke(this, layoutInflater)!!.saveAsUnChecked()
        setContentView(mBinding.root)
    }
}