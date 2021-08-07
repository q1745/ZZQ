package com.example.usercenter.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.mvp_core.view.BaseMVPActivity
import com.example.usercenter.R
import com.example.usercenter.contract.UserCenterLoginContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.presenter.UserCenterLoginPresenterImpl
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

/**
 *@author:ZZQ
 *@date:2021/7/25
 */
class LoginActivity : BaseMVPActivity<UserCenterLoginPresenterImpl>(), UserCenterLoginContract.UserCenterLoginView{

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initEvent() {
        login.setOnClickListener {
            val user = login_user.text.toString()
            val pass = login_pass.text.toString()

            mPresenter.login(UserEntitiy("2001",1,pass,"1",user))
        }

        go_reg.setOnClickListener {
            startActivity<RegActivity>()
        }

        go_code.setOnClickListener {
            startActivity<YzCodeActivity>()
        }
    }

    override fun createPresenter(): UserCenterLoginPresenterImpl {
        return UserCenterLoginPresenterImpl(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun relaseResource() {

    }

    override fun <T> loginSuccess(data: T) {
        Log.d("123", "loginSuccess: ${data.toString()}")
        if (data == 200) {
            startActivity<MainActivity>()
        }
    }

    override fun loginFailed(throwable: Throwable) {
        Log.e("123", "loginFailed: ${throwable.message}" )
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }
}