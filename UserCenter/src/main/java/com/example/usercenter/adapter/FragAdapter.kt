package com.example.usercenter.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 *@author:ZZQ
 *@date:2021/7/26
 */
class FragAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm) {
    var list: MutableList<Fragment> = mutableListOf()

    constructor(fm: FragmentManager, behavior: Int, list: MutableList<Fragment>) : this(fm, behavior) {
        this.list = list
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int) : Fragment {
        return list.get(position)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

    }

}