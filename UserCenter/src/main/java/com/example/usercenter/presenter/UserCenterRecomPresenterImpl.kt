package com.example.usercenter.presenter

import com.example.net.rxjava.BIIObserver
import com.example.net.rxjava.ObservableUtils
import com.example.usercenter.bean.RecomBean
import com.example.usercenter.contract.UserCenterCodeContract
import com.example.usercenter.contract.UserCenterRecomContract
import com.example.usercenter.repository.UserCenterRecomRepositoryImpl


/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterRecomPresenterImpl(view : UserCenterRecomContract.UserCenterView) : UserCenterRecomContract.UserCenterPresenter(view) {

    override fun recomm() {
        ObservableUtils.getInstance().doObservable(mRepository.recomm(), object :
        BIIObserver<RecomBean>() {
            override fun onNext(t: RecomBean) {
                super.onNext(t)
                mView.get()!!.success(t.data)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mView.get()!!.faile(e)
            }
        },mView.get()!!.getLifecycleOwner())
    }

    override fun createRepository(): UserCenterRecomContract.UserCenterRepository {
        return UserCenterRecomRepositoryImpl()
    }
}