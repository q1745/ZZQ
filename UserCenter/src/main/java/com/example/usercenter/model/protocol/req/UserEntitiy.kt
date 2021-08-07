package com.example.usercenter.model.protocol.req

/**
 *@author:ZZQ
 *@date:2021/7/23
 */
data class UserEntitiy(
    val birthday: String,
    val id: Int,
    val pwd: String,
    val sex: String,
    val username: String
)
