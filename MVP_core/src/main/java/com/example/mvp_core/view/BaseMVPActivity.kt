package com.example.mvp_core.view

import com.example.mvp_core.BasePresenter

/**
 *@author:ZZQ
 *@date:2021/7/22
 */
abstract class BaseMVPActivity<P:BasePresenter<*,*>> : BaseActivity(){
    
    protected lateinit var mPresenter: P
    
    init {
        mPresenter = createPresenter()
    }

    abstract fun createPresenter(): P

}