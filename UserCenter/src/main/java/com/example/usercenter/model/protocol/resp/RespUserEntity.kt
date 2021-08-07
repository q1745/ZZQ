package com.example.usercenter.model.protocol.resp

/**
 *@author:ZZQ
 *@date:2021/7/23
 * 服务器响应的用户实体类型
 */
data class RespUserEntity(
    val birthday: String,
    val id: Int,
    val pwd: String,
    val sex: String,
    val username: String
)
