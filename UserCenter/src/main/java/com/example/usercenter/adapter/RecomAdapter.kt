package com.example.usercenter.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.common.app.MyApplication
import com.example.usercenter.R
import com.example.usercenter.bean.RecomBean
import com.example.usercenter.ui.MainActivity
import kotlin.random.Random

/**
 *@author:ZZQ
 *@date:2021/7/31
 */
class RecomAdapter(var context: Context, var list: MutableList<RecomBean.DataBean>) : RecyclerView.Adapter<RecomAdapter.Holder>() {

    var colors : MutableList<Int> = mutableListOf(Color.RED,Color.GREEN,Color.BLUE,Color.BLACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflate = LayoutInflater.from(context).inflate(R.layout.recommend, null)
        return Holder(inflate)
    }

    inner class Holder(vie: View) : RecyclerView.ViewHolder(vie) {
        var img : ImageView = vie.findViewById(R.id.reco_img)
        var tv1 : TextView = vie.findViewById(R.id.reco_tv)
        var tv2 : TextView = vie.findViewById(R.id.reco_tv2)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var name = list.get(position).category_name.toString()
        val up = name.substring(0, 2)
        if (name.length >= 4) {
            name = name.substring(0,4)
        } else if (name.length == 2) {
            name = name + "模块"
        }
        holder.img.setBackgroundColor(colors.get(Random.nextInt(colors.size)))
//        holder.img.background = colors.get(Random.nextInt(colors.size))
        holder.tv1.text = up
        holder.tv2.text = name
    }

    override fun getItemCount(): Int {
        return list.size
    }

}