package com.example.usercenter.repository

import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.contract.UserCenterLoginContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import com.example.usercenter.model.service.UserCenterLoginModelImpl
import io.reactivex.Observable

/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterLoginRepositoryImpl : UserCenterLoginContract.UserCenterLoginRepository(){
    override fun login(userEntitiy: UserEntitiy): Observable<BaseRespEntity<RespUserEntity>> {
        return mModel.login(userEntitiy)
    }

    override fun createModel(): UserCenterLoginContract.UserCenterLoginModel {
        return UserCenterLoginModelImpl()
    }
}