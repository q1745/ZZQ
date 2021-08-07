package com.example.mvp_core.view

import android.os.Bundle
import com.example.mvp_core.BasePresenter

/**
 *@author:ZZQ
 *@date:2021/7/31
 */
abstract class BaseMVPFragment<P: BasePresenter<*,*>> : BaseFragment(){
    protected lateinit var mPresenter: P

    init {
        mPresenter = createPresenter()
    }

    abstract fun createPresenter(): P
}