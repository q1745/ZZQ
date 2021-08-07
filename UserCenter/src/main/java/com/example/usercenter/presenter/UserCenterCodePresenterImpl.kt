package com.example.usercenter.presenter

import com.example.net.rxjava.BIIObserver
import com.example.net.rxjava.ObservableUtils
import com.example.usercenter.contract.UserCenterCodeContract
import com.example.usercenter.repository.UserCenterCodeRepositoryImpl


/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterCodePresenterImpl(view : UserCenterCodeContract.UserCenterView) : UserCenterCodeContract.UserCenterPresenter(view) {

    override fun getcode() {
        ObservableUtils.getInstance().doObservable(mRepository.reg(), object :
        BIIObserver<String>() {
            override fun onNext(t: String) {
                super.onNext(t)
                mView.get()!!.success(t)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mView.get()!!.faile(e)
            }
        },mView.get()!!.getLifecycleOwner())
    }

    override fun createRepository(): UserCenterCodeContract.UserCenterRepository {
        return UserCenterCodeRepositoryImpl()
    }
}