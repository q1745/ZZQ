package com.example.usercenter.ui

import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.example.mvp_core.view.BaseActivity
import com.example.usercenter.NewWorkStateReceiver
import com.example.usercenter.R
import com.example.usercenter.adapter.FragAdapter
import com.example.usercenter.ui.fragment.*
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarBackground
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarConfig
import kotlinx.android.synthetic.main.activity_main.*

/**
 *@author:ZZQ
 *@date:2021/7/26
 */
class MainActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener {

    var fragments: MutableList<Fragment> = mutableListOf()
    var network: NewWorkStateReceiver? = null

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onResume() {
        if (network == null) {
            network = NewWorkStateReceiver()
        }
        var filter: IntentFilter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(network, filter)
        println("注册")
        super.onResume()
    }

    override fun onPause() {
        unregisterReceiver(network)
        println("注销")
        super.onPause()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initData(savedInstanceState: Bundle?) {

        /**
         * 沉浸式布局
         */
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = Color.TRANSPARENT


//        val barBackground: BarBackground = BarBackground.Companion.newInstance()
//            .color(Color.TRANSPARENT)
//            .transparent()
//
//        val barConfig: BarConfig = BarConfig.Companion.newInstance()
//            .background(barBackground)
//            .light(true)
//
//        UltimateBarX.with(this)
//            .config(barConfig)
//            .applyNavigationBar()
//        UltimateBarX.with(this)
//            .config(barConfig)
//            .applyStatusBar()



        fragments.add(IndexFragment())
        fragments.add(TypeFragment())
        fragments.add(FindFragment())
        fragments.add(ShopFragment())
        fragments.add(MineFragment())
        main_vp.adapter = FragAdapter(this@MainActivity.supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments)

        main_bottombar.setActiveColor("#9C27B0")
            .setInActiveColor("#4CAF50")
            .setMode(BottomNavigationBar.MODE_FIXED)
            .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)

        var text = TextBadgeItem()
        text.setBackgroundColor(Color.RED).setText("66").setTextColor(Color.WHITE)

        main_bottombar.addItem(BottomNavigationItem(R.drawable.index,"首页"))
            .addItem(BottomNavigationItem(R.drawable.type,"分类"))
            .addItem(BottomNavigationItem(R.drawable.find,"发现"))
            .addItem(BottomNavigationItem(R.drawable.shop,"购物车").setBadgeItem(text))
            .addItem(BottomNavigationItem(R.drawable.mine,"我的"))
            .initialise()

        main_bottombar.setTabSelectedListener(this)

    }

    override fun initEvent() {
        main_vp.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                main_bottombar.selectTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    override fun relaseResource() {

    }

    override fun onTabSelected(position: Int) {
        main_vp.currentItem = position
    }

    override fun onTabUnselected(position: Int) {

    }

    override fun onTabReselected(position: Int) {

    }
}