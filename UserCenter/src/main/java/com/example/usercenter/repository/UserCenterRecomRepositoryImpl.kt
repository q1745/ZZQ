package com.example.usercenter.repository

import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.bean.RecomBean
import com.example.usercenter.contract.UserCenterCodeContract
import com.example.usercenter.contract.UserCenterLoginContract
import com.example.usercenter.contract.UserCenterRecomContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import com.example.usercenter.model.service.UserCenterCodeModelImpl
import com.example.usercenter.model.service.UserCenterLoginModelImpl
import com.example.usercenter.model.service.UserCenterRecomModelImpl
import io.reactivex.Observable

/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterRecomRepositoryImpl : UserCenterRecomContract.UserCenterRepository(){

    override fun recomm(): Observable<RecomBean> {
        return mModel.recomm()
    }

    override fun createModel(): UserCenterRecomContract.UserCenterModel {
        return UserCenterRecomModelImpl()
    }

}