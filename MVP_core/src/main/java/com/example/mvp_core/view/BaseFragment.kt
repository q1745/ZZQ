package com.example.mvp_core.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.common.ConstValue

/**
 *@author:ZZQ
 *@date:2021/7/31
 */
abstract class BaseFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData(savedInstanceState)
        initEvent()
        if (isClearStatBar()) {
            ClearStatBar()
        }
    }

    /**
     * 跳转Activity
     */
    fun jumpActivity(otherActivity: Class<*>) {
        startActivity(Intent(activity,otherActivity))
    }

    /**
     * 带参数跳转Activity
     */
    fun jumpActivity(otherActivity: Class<*>, params:Bundle?) {
        val intent: Intent = Intent(activity,otherActivity)
        intent.putExtra(ConstValue.JUMP_ACTIVITY_PARAMS_KEY,params)
        startActivity(intent)
    }

    /**
     * 显示消息
     */
    fun showMsg(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    /**
     * 获取布局
     */
    abstract fun getLayout(): Int

    /**
     * 去掉状态栏  实现沉浸式
     */
    fun ClearStatBar() {

    }

    /**
     * 是否实现沉浸式布局
     */
    protected open fun isClearStatBar(): Boolean {
        return true
    }

    /**
     * 初始化事件
     */
    abstract fun initEvent()

    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)

    /**
     * 释放资源
     */
    abstract fun relaseResource()

    override fun onStop() {
        super.onStop()
        relaseResource()
    }
}


