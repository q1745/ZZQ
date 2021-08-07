package com.example.common.sp

import android.content.Context
import android.content.SharedPreferences
import com.example.common.app.MyApplication
import java.lang.IllegalArgumentException

/**
 *@author:ZZQ
 *@date:2021/7/24
 */
object SpUtils {

    private val sp:SharedPreferences by lazy {
        createSharedPreferences()
    }

    private fun createSharedPreferences() : SharedPreferences {
        return MyApplication.getAppContext().getSharedPreferences("cc",Context.MODE_PRIVATE)
    }

    fun<T> putValue(key: String, value: T, isCommit: Boolean) {
        sp.edit().apply() {
            when(value) {
                is String -> putString(key,value)
                is Float -> putFloat(key,value)
                is Boolean -> putBoolean(key,value)
                is Long -> putLong(key,value)
                is Int -> putInt(key,value)
                else -> throw IllegalArgumentException("you use type is unknown...")
            }
            if (isCommit) commit() else apply()
        }
    }

    fun <T> getValue(key:String,defaultValue:T) : T{
        return when(defaultValue){
            is String -> sp.getString(key,defaultValue)
            is Float -> sp.getFloat(key,defaultValue)
            is Boolean -> sp.getBoolean(key,defaultValue)
            is Int -> sp.getInt(key,defaultValue)
            is Long -> sp.getLong(key,defaultValue)
            else->throw IllegalArgumentException("you get value type is unknown...")
        } as T
    }
}