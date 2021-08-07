package com.example.net.api

import com.zy.net.protocol.resp.TokenRespEntity
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *@author:ZZQ
 *@date:2021/7/23
 */
interface TokenApi {

    @FormUrlEncoded
    @POST("token")
    fun getToken(
        @Field("grant_type") grant_type: String?,
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Call<TokenRespEntity?>?
}