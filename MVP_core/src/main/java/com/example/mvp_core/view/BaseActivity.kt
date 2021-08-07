package com.example.mvp_core.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.common.ConstValue

/**
 *@author:ZZQ
 *@date:2021/7/22
 */
abstract class BaseActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayout())
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
        startActivity(Intent(this@BaseActivity,otherActivity))
    }

    /**
     * 带参数跳转Activity
     */
    fun jumpActivity(otherActivity: Class<*>, params:Bundle?) {
        val intent:Intent = Intent(this@BaseActivity,otherActivity)
        intent.putExtra(ConstValue.JUMP_ACTIVITY_PARAMS_KEY,params)
        startActivity(intent)
    }

    /**
     * 显示消息
     */
    fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
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