package com.example.usercenter.contract

import com.example.mvp_core.BasePresenter
import com.example.mvp_core.BaseRepository
import com.example.mvp_core.IModel
import com.example.mvp_core.IView
import com.example.net.protocol.resp.BaseRespEntity
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import io.reactivex.Observable

/**
 *@author:ZZQ
 *@date:2021/7/25
 */
interface UserCenterLoginContract {

    interface UserCenterLoginModel: IModel {
        fun login(userEntitiy: UserEntitiy) : Observable<BaseRespEntity<RespUserEntity>>
    }

    abstract class UserCenterLoginRepository: BaseRepository<UserCenterLoginModel>() {
        abstract fun login(userEntitiy: UserEntitiy): Observable<BaseRespEntity<RespUserEntity>>
    }

    interface UserCenterLoginView : IView {
        fun <T> loginSuccess(data:T)
        fun loginFailed(throwable: Throwable)
    }

    abstract class UserCenterLoginPresenter(view: UserCenterLoginView) : BasePresenter<UserCenterLoginRepository,UserCenterLoginView>(view) {
        abstract fun login(userEntitiy: UserEntitiy)
    }
}