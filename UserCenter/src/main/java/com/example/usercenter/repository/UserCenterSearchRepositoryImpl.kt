package com.example.usercenter.repository

import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.bean.SearchBean
import com.example.usercenter.contract.UserCenterCodeContract
import com.example.usercenter.contract.UserCenterLoginContract
import com.example.usercenter.contract.UserCenterSearchContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import com.example.usercenter.model.service.UserCenterCodeModelImpl
import com.example.usercenter.model.service.UserCenterLoginModelImpl
import com.example.usercenter.model.service.UserCenterSearchModelImpl
import io.reactivex.Observable

/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterSearchRepositoryImpl : UserCenterSearchContract.UserCenterRepository(){

    override fun search(string: String): Observable<SearchBean> {
        return mModel.search(string)
    }

    override fun createModel(): UserCenterSearchContract.UserCenterModel {
        return UserCenterSearchModelImpl()
    }

}