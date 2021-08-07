package com.example.common.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.multidex.MultiDex

/**
 *@author:ZZQ
 *@date:2021/7/23
 */
class MyApplication : Application(){

//    lateinit var activitys : MutableList<Activity>
//
//    init {
//        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks{
//            override fun onActivityCreated(p0: Activity, p1: Bundle?) {
//                activitys.add(p0)
//                Log.d("123", "onActivityCreated: " + p0.toString())
//            }
//
//            override fun onActivityStarted(p0: Activity) {
//                Log.d("123", "onActivityStarted: " + p0.toString())
//            }
//
//            override fun onActivityResumed(p0: Activity) {
//                Log.d("123", "onActivityResumed: " + p0.toString())
//            }
//
//            override fun onActivityPaused(p0: Activity) {
//                Log.d("123", "onActivityPaused: " + p0.toString())
//            }
//
//            override fun onActivityStopped(p0: Activity) {
//                Log.d("123", "onActivityStopped: " + p0.toString())
//            }
//
//            override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
//            }
//
//            override fun onActivityDestroyed(p0: Activity) {
//                activitys.remove(p0)
//            }
//
//        })
//    }

    override fun onCreate() {
        super.onCreate()
        mContext = this

    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        var mContext : Application? = null
        fun getAppContext() : Context {
            return mContext!!
        }
    }

//    fun exitApp() {
//        for (activity in activitys) {
//            activity.finish()
//        }
//    }
}