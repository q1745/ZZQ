package com.example.usercenter.repository

import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.contract.UserCenterCodeContract
import com.example.usercenter.contract.UserCenterLoginContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import com.example.usercenter.model.service.UserCenterCodeModelImpl
import com.example.usercenter.model.service.UserCenterLoginModelImpl
import io.reactivex.Observable

/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterCodeRepositoryImpl : UserCenterCodeContract.UserCenterRepository(){

    override fun reg(): Observable<String> {
        return mModel.code()
    }

    override fun createModel(): UserCenterCodeContract.UserCenterModel {
        return UserCenterCodeModelImpl()
    }

}