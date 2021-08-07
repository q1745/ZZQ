package com.example.usercenter.model.api

import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.bean.RecomBean
import com.example.usercenter.bean.SearchBean
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import io.reactivex.Observable
import retrofit2.http.*

/**
 *@author:ZZQ
 *@date:2021/7/22
 */
interface UserCenterApi {

    /**
     * 注册
     */
    @POST("api/User/register")
    fun register(@Body params: UserEntitiy): Observable<BaseRespEntity<RespUserEntity>>

    /**
     * 登录
     */
    @POST("api/User/login")
    fun login(@Body params: UserEntitiy) : Observable<BaseRespEntity<RespUserEntity>>

    /**
     * 获取验证码
     */
    @GET("api/User/authcode")
    fun code() : Observable<String>

    /**
     * 获取推荐分类
     */
    @GET("api/GoodsType/getRecommendTypes")
    fun recomm() : Observable<RecomBean>

    /**
     * 获取搜索数据
     */
    @GET("api/Goods/getGoods?")
    fun sea(@Query("keyword") str : String, @Query("type") string: String, @Query("pageno") int: Int, @Query("pagesize") pagesize : Int) : Observable<SearchBean>
}