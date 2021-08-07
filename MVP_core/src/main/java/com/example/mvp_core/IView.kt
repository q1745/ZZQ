package com.example.mvp_core

import androidx.lifecycle.LifecycleOwner

interface IView {

    /**
     * 获取生命周期拥有者
     */
    fun getLifecycleOwner():LifecycleOwner

}