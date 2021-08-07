package com.example.usercenter.presenter

import com.example.net.protocol.resp.BaseRespEntity
import com.example.net.rxjava.BIIObserver
import com.example.net.rxjava.ObservableUtils
import com.example.usercenter.contract.UserCenterLoginContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import com.example.usercenter.repository.UserCenterLoginRepositoryImpl


/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterLoginPresenterImpl(view : UserCenterLoginContract.UserCenterLoginView) : UserCenterLoginContract.UserCenterLoginPresenter(view) {
    override fun login(userEntitiy: UserEntitiy) {
        ObservableUtils.getInstance().doObservable(mRepository.login(userEntitiy), object :
            BIIObserver<BaseRespEntity<RespUserEntity>>() {
            override fun onNext(t: BaseRespEntity<RespUserEntity>) {
                super.onNext(t)
                mView.get()!!.loginSuccess(t.code)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mView.get()!!.loginFailed(e)
            }
            },mView.get()!!.getLifecycleOwner())
    }

    override fun createRepository(): UserCenterLoginContract.UserCenterLoginRepository {
        return UserCenterLoginRepositoryImpl()
    }
}