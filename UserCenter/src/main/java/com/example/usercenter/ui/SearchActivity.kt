package com.example.usercenter.ui

import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvp_core.view.BaseActivity
import com.example.mvp_core.view.BaseMVPActivity
import com.example.usercenter.R
import com.example.usercenter.adapter.SearchAdapter
import com.example.usercenter.bean.RecomBean
import com.example.usercenter.bean.SearchBean
import com.example.usercenter.contract.UserCenterSearchContract
import com.example.usercenter.presenter.UserCenterSearchPresenterImpl
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.fragment_index.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 *@author:ZZQ
 *@date:2021/8/1
 */
class SearchActivity : BaseMVPActivity<UserCenterSearchPresenterImpl>(), UserCenterSearchContract.UserCenterView{
    override fun getLayout(): Int {
        return R.layout.activity_search
    }

    override fun initEvent() {

        canle.setOnClickListener {
            startActivity<MainActivity>()
        }
        seach_true.setOnClickListener {
            val searc = seach_true.text.toString().trim()
            if (searc.length == 0) {
                return@setOnClickListener
            } else {
                mPresenter.getsearch(searc)
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        search_rec.layoutManager = LinearLayoutManager(this)
    }

    override fun relaseResource() {

    }

    override fun createPresenter(): UserCenterSearchPresenterImpl {
        return UserCenterSearchPresenterImpl(this)
    }

    override fun <T> success(string: T) {
        var aa = string as SearchBean
        var list : MutableList<SearchBean.Data> = aa.data
        search_rec.adapter = SearchAdapter(this, list)

    }

    override fun faile(throwable: Throwable) {
        Log.e("123", "faile: ${throwable.message}" )
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }
}