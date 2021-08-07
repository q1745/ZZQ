package com.example.usercenter.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.example.common.sp.SPPropDelegate
import com.example.mvp_core.view.BaseMVPActivity
import com.example.usercenter.R
import com.example.usercenter.contract.UserCenterContract
import com.example.usercenter.model.protocol.req.UserEntitiy
import com.example.usercenter.presenter.UserCenterPresenterImpl
import kotlinx.android.synthetic.main.activity_reg.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class RegActivity : BaseMVPActivity<UserCenterPresenterImpl>(), UserCenterContract.UserCenterView {
    private var username:String by SPPropDelegate<String>("username","",false)
    override fun getLayout(): Int {
        return R.layout.activity_reg
    }

    override fun initEvent() {
        btn_reg.setOnClickListener {
            toast("哈哈哈")
            val phoneNumber : String = ed_reg_phonenumber.text.trim().toString()
            val pwd : String = ed_reg_pwd.text.trim().toString()
            username = phoneNumber
            mPresenter.reg(UserEntitiy("2001-01-01",0,pwd,"1",phoneNumber))
        }
    }

    override fun createPresenter(): UserCenterPresenterImpl {
        return UserCenterPresenterImpl(this)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    /**
     * OnStop中调用，可以释放资源。如移除handler，停止动画
     */
    override fun relaseResource() {

    }

    override fun <T> regSuccess(data: T) {
        Log.d("123", "regSuccess:  ${data.toString()}")
        startActivity<LoginActivity>()
    }

    override fun regFailed(throwable: Throwable) {
        Log.w("123", "regFailed: ${throwable.message}")
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }


}