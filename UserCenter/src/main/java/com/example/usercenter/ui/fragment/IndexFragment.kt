package com.example.usercenter.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.common.app.MyApplication
import com.example.mvp_core.view.BaseMVPFragment
import com.example.usercenter.R
import com.example.usercenter.adapter.RecomAdapter
import com.example.usercenter.bean.RecomBean
import com.example.usercenter.contract.UserCenterRecomContract
import com.example.usercenter.presenter.UserCenterRecomPresenterImpl
import com.example.usercenter.ui.SearchActivity
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_index.*
import java.net.URL
import java.net.URLConnection
import java.text.SimpleDateFormat
import java.util.*
import javax.security.auth.callback.Callback

/**
 *@author:ZZQ
 *@date:2021/7/31
 */
class IndexFragment : BaseMVPFragment<UserCenterRecomPresenterImpl>(), UserCenterRecomContract.UserCenterView{

    var time : String? = null   //接收标准北京时间
    var images : MutableList<Int> = mutableListOf()
    var timer : Timer? = null       //timer
    var subHour : Int = 0       //截取网络时间，接收小时
    var subMinute : Int = 0   //截取网络时间，接收分钟
    var subSecond : Int = 0   //截取网络时间，接收秒
    var nextHour : Int = 0   //接收距离下一个场次的小时
    var nextMinute : Int = 60   //接收距离下一个场次的分钟
    var nextSecond : Int = 60   //接收距离下一个场次的秒
    var textFirst : String = ""
    var textSecond : String = ""

    var handler : Handler = object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 100) {
                subHour = time!!.substring(11, 13).toInt()   //获取小时
                subMinute = time!!.substring(14,16).toInt()  //获取分钟
                subSecond = time!!.substring(17,19).toInt()  //获取秒
                nextMinute = 60 - subMinute
                nextSecond = 60 - subSecond
                //判断场次
                if (subHour!! >= 8 && subHour!! < 10) {
                    nextHour = 10 - subHour - 1
                    index_hour.text = "10点场"
                } else if (subHour!! >= 10 && subHour!! < 16) {
                    nextHour = 16 - subHour - 1
                    index_hour.text = "16点场"
                } else if (subHour!! >= 16 && subHour!! < 18) {
                    nextHour = 18 - subHour - 1
                    index_hour.text = "18点场"
                } else if (subHour!! >= 18 && subHour!! < 20) {
                    nextHour = 20 - subHour - 1
                    index_hour.text = "20点场"
                } else if (subHour!! >= 20 && subHour!! < 22) {
                    nextHour = 22 - subHour - 1
                    index_hour.text = "22点场"
                }
                if(nextHour <= 0) {
                    textFirst = "00"
                } else if (nextHour < 10){
                    textFirst = "0" + nextHour.toString()
                }

                if (nextMinute!! < 10 && nextSecond < 10) {
                    textSecond = ":0${nextMinute}:0${nextSecond}"
                } else if (nextMinute !! < 10 && nextSecond >= 10) {
                    textSecond = ":0${nextMinute}:${nextSecond}"
                } else if (nextMinute !! >= 10 && nextSecond < 10) {
                    textSecond = ":${nextMinute}:0${nextSecond}"
                } else {
                    textSecond = ":${nextMinute}:${nextSecond}"
                }
                index_time.text = textFirst + textSecond
            }
        }
    }


    override fun getLayout(): Int {
        return R.layout.fragment_index
    }

    override fun initEvent() {

        seach.setOnClickListener {
            val intent = Intent(activity,SearchActivity::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("MissingPermission")
    override fun initData(savedInstanceState: Bundle?) {

        //获取时间
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                var web : String = "http://www.baidu.com"
                val url = URL(web)
                val openConnection = url.openConnection() as URLConnection
                openConnection.connect()   //发出链接
                var webTime = openConnection.date as Long //读取网站时间
                var date : Date = Date(webTime)      //转为标准时间
                val simpleDateFormat : SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)   //转为北京时间
                time = simpleDateFormat.format(date)
                handler.sendEmptyMessage(100)
            }
        },0,1000)

        //banner
        ban()
        mPresenter.recomm()
        index_rec.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)

    }



    //banner
    fun ban() {
        images.add(R.drawable.aaaa)
        images.add(R.drawable.dddd)
        images.add(R.drawable.eeee)
        images.add(R.drawable.ffff)
        images.add(R.drawable.gggg)
        images.add(R.drawable.hhhh)
        images.add(R.drawable.iiii)
        images.add(R.drawable.jjjj)
        images.add(R.drawable.kkkk)

        ban.setImages(images)
        ban.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                imageView!!.setImageResource(path as Int)
            }
        })

        ban.setBannerAnimation(com.youth.banner.Transformer.FlipHorizontal)
        ban.setBannerStyle(Banner.AUTOFILL_FLAG_INCLUDE_NOT_IMPORTANT_VIEWS)
        ban.setDelayTime(1000)
        ban.start()
    }

    override fun createPresenter(): UserCenterRecomPresenterImpl {
        return UserCenterRecomPresenterImpl(this)
    }

    override fun relaseResource() {
        timer!!.cancel()
        handler.removeCallbacksAndMessages(null)
    }

    override fun <T> success(string: T) {
        var list:MutableList<RecomBean.DataBean> = string as MutableList<RecomBean.DataBean>
        index_rec.adapter = MyApplication.mContext?.let { RecomAdapter(it,list) }
    }

    override fun faile(throwable: Throwable) {
        Toast.makeText(activity, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }

    override fun onDestroy() {
        relaseResource()
        super.onDestroy()
    }
}


