package com.example.net.protocol.resp

/**
 *@author:ZZQ
 *@date:2021/7/23
 * 服务器返回的基础类型结构
 *
 */
data class BaseRespEntity<T>(var code:Int,var data:T,var msg:String)
