package com.example.usercenter.presenter

import com.example.net.protocol.resp.BaseRespEntity
import com.example.net.rxjava.BIIObserver
import com.example.net.rxjava.ObservableUtils
import com.example.usercenter.contract.UserCenterContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.model.protocol.resp.RespUserEntity
import com.example.usercenter.repository.UserCenterRegRepositoryImpl

/**
 *@author:ZZQ
 *@date:2021/7/22
 */
class UserCenterPresenterImpl(view: UserCenterContract.UserCenterView) : UserCenterContract.UserCenterPresenter(view){

    override fun reg(userEntitiy: UserEntitiy) {
        ObservableUtils.getInstance().doObservable(mRepository.reg(userEntitiy),object :
            BIIObserver<BaseRespEntity<RespUserEntity>>() {
            override fun onNext(t: BaseRespEntity<RespUserEntity>) {
                super.onNext(t)
                mView.get()!!.regSuccess(t)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mView.get()!!.regFailed(e)
            }
            },mView.get()!!.getLifecycleOwner())
    }

    override fun createRepository(): UserCenterContract.UserCenterRepository {
        return UserCenterRegRepositoryImpl()
    }
}