package com.example.usercenter.model.service

import com.example.net.RetrofitFactory
import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.contract.UserCenterContract
import com.example.usercenter.model.api.UserCenterApi
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import io.reactivex.Observable

/**
 *@author:ZZQ
 *@date:2021/7/23
 */
class UserCenterModelImpl : UserCenterContract.UserCenterModel {

    override fun reg(userEntitiy: UserEntitiy): Observable<BaseRespEntity<RespUserEntity>> {

        val create = RetrofitFactory.retrofitFactory.create(UserCenterApi::class.java)
        return create.register(userEntitiy)
    }

}