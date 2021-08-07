package com.example.usercenter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.widget.Toast
import com.example.common.app.MyApplication
import java.lang.StringBuilder

/**
 *@author:ZZQ
 *@date:2021/7/29
 */
class NewWorkStateReceiver : BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        println("网络状态发生变化")

        //判断API 版本是否小于23，小于23执行一种方法，不小于是另外一种
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            val connMgr = MyApplication.mContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val wiflnetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (wiflnetworkInfo!!.isConnected && networkInfo!!.isConnected) {
                Toast.makeText(MyApplication.mContext, "WIFI已连接，移动数据已连接", Toast.LENGTH_SHORT).show()
            } else if (wiflnetworkInfo.isConnected && !networkInfo!!.isConnected) {
                Toast.makeText(MyApplication.mContext, "WIFI已连接，移动数据未连接", Toast.LENGTH_SHORT).show()
            } else if (!wiflnetworkInfo.isConnected && networkInfo!!.isConnected) {
                Toast.makeText(MyApplication.mContext, "WIFI未连接，移动数据已连接", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(MyApplication.mContext, "WIFI未连接，移动数据未连接", Toast.LENGTH_SHORT).show()
            }
        } else {
            println("API 大于23")
            println("我是伞兵志强")
            val connMgr = MyApplication.mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val works: MutableList<Network> = connMgr.allNetworks.toMutableList()
            var sb : StringBuilder = StringBuilder()
            for (i in works) {
                val networkInfo = connMgr.getNetworkInfo(i)
                sb.append(networkInfo!!.typeName + " connect is " + networkInfo.isConnected)
            }
            Toast.makeText(MyApplication.mContext, sb.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}