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
 *@date:2021/7/22
 */
interface UserCenterCodeContract {

    interface UserCenterModel : IModel {
        fun code() : Observable<String>
    }

    abstract class UserCenterRepository:BaseRepository<UserCenterModel>() {
        abstract fun reg() : Observable<String>
    }

    interface UserCenterView: IView {
        fun <T> success(string: T)
        fun faile(throwable: Throwable)
    }

    abstract class UserCenterPresenter(view: UserCenterView): BasePresenter<UserCenterRepository,UserCenterView>(view) {
        abstract fun getcode()
    }
}
