package com.example.usercenter.repository

import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.contract.UserCenterContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import com.example.usercenter.model.service.UserCenterModelImpl
import io.reactivex.Observable

/**
 *@author:ZZQ
 *@date:2021/7/22
 */
class UserCenterRegRepositoryImpl : UserCenterContract.UserCenterRepository() {

    override fun reg(userEntitiy: UserEntitiy): Observable<BaseRespEntity<RespUserEntity>> {
        return mModel.reg(userEntitiy)
    }

    override fun createModel(): UserCenterContract.UserCenterModel {
        return UserCenterModelImpl()
    }
}