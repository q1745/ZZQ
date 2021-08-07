package com.example.usercenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.common.app.MyApplication
import com.example.usercenter.R
import com.example.usercenter.bean.SearchBean

/**
 *@author:ZZQ
 *@date:2021/8/2
 */
class SearchAdapter(var context: Context, var list: MutableList<SearchBean.Data>) :
    RecyclerView.Adapter<SearchAdapter.Holder>() {

    inner class Holder(view : View) : RecyclerView.ViewHolder(view) {
        var imageView = view.findViewById(R.id.search_img) as ImageView
        var title = view.findViewById(R.id.search_tv) as TextView
        var money = view.findViewById(R.id.search_money) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflate = LayoutInflater.from(MyApplication.getAppContext())
            .inflate(R.layout.search_show, parent, false)
        return Holder(inflate)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        Glide.with(MyApplication.getAppContext()).load(list.get(position).PictUrl).into(holder.imageView)
        holder.title.text = list.get(position).Title
        holder.money.text = list.get(position).ReservePrice
    }

    override fun getItemCount(): Int {
        return list.size
    }
}