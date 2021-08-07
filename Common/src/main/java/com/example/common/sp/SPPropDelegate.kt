package com.example.common.sp

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 *@author:ZZQ
 *@date:2021/7/23
 */
class SPPropDelegate<T>(
    //存的属性名
    private val key:String,
    //返回默认值
    private val defaultValue:T,
    //是否是commit提交方式
    private val isCommit:Boolean = true
) : ReadWriteProperty<Any?,T>{
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        //提高健壮性
        var finalKey = if (key.isNotBlank()) property.name else key
        return SpUtils.getValue(finalKey,defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        SpUtils.putValue(key,value,isCommit)
    }
}