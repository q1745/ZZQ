package com.example.usercenter.presenter

import com.example.net.rxjava.BIIObserver
import com.example.net.rxjava.ObservableUtils
import com.example.usercenter.bean.SearchBean
import com.example.usercenter.contract.UserCenterCodeContract
import com.example.usercenter.contract.UserCenterSearchContract
import com.example.usercenter.repository.UserCenterCodeRepositoryImpl
import com.example.usercenter.repository.UserCenterSearchRepositoryImpl


/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class UserCenterSearchPresenterImpl(view : UserCenterSearchContract.UserCenterView) : UserCenterSearchContract.UserCenterPresenter(view) {

    override fun getsearch(string: String) {
        ObservableUtils.getInstance().doObservable(mRepository.search(string), object :
        BIIObserver<SearchBean>() {
            override fun onNext(t: SearchBean) {
                super.onNext(t)
                mView.get()!!.success(t)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
                mView.get()!!.faile(e)
            }
        },mView.get()!!.getLifecycleOwner())
    }

    override fun createRepository(): UserCenterSearchContract.UserCenterRepository {
        return UserCenterSearchRepositoryImpl()
    }
}