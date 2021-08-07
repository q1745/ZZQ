package com.example.usercenter.model.service

import com.example.net.RetrofitFactory
import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.contract.UserCenterCodeContract
import com.example.usercenter.contract.UserCenterLoginContract
import com.example.usercenter.model.api.UserCenterApi
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import io.reactivex.Observable

/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterCodeModelImpl : UserCenterCodeContract.UserCenterModel{

    override fun code(): Observable<String> {
        val create = RetrofitFactory.retrofitFactory.create(UserCenterApi::class.java)
        return create.code()
    }

}