package com.example.usercenter.ui

import android.app.Notification
import android.app.NotificationManager
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.example.mvp_core.view.BaseMVPActivity
import com.example.usercenter.R
import com.example.usercenter.contract.UserCenterCodeContract
import com.example.usercenter.presenter.UserCenterCodePresenterImpl
import kotlinx.android.synthetic.main.activity_yzcode.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*

/**
 *@author:ZZQ
 *@date:2021/7/27
 */
class YzCodeActivity : BaseMVPActivity<UserCenterCodePresenterImpl>(), UserCenterCodeContract.UserCenterView{

    var code : String? = null
    var count : Int = 60

    override fun getLayout(): Int {
        return R.layout.activity_yzcode
    }

    override fun initEvent() {


        getcode.setOnClickListener {
            val toString = login_code_user.text.toString()
            if (toString.length == 0) {
                toast("不能为空")
                return@setOnClickListener
            }
            val zz = "^[1]{1}[3|4|5|8]{1}[0-9]{9}$"
            if (!toString.matches(Regex(zz))) {
                toast("手机号格式有误")
                return@setOnClickListener
            }
            mPresenter.getcode()
        }

        code_login.setOnClickListener {
            val toString = login_code_pass.text.toString()
            if (code == null) {
                return@setOnClickListener
            } else {
                if (code.equals(toString)) {
                    toast("登录成功！")
                    startActivity<MainActivity>()
                } else{
                    toast("验证码错误")
                }
            }
        }

        go_pass_login.setOnClickListener {
            startActivity<LoginActivity>()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun relaseResource() {

    }

    override fun createPresenter(): UserCenterCodePresenterImpl {
        return UserCenterCodePresenterImpl(this)
    }

    override fun <T> success(string: T) {
        code = string.toString()
        var notification = Notification.Builder(this)
        notification.setSmallIcon(R.mipmap.ic_launcher)
        notification.setContentTitle("获取验证码")
        notification.setContentText("${string}")
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1,notification.build())
        getcode.isEnabled = false
        var timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread(object : Runnable {
                    override fun run() {
                        getcode.text = "请在${count}秒后获取"
                        count--;
                        if (count <= 0) {
                            timer.cancel()
                            getcode.isEnabled = true
                            getcode.text = "获取验证码"
                        }
                    }

                })
            }

        },1,100)
    }

    override fun faile(throwable: Throwable) {
        toast(throwable.message.toString())
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }
}