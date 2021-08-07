package com.example.usercenter.bean

/**
 *@author:ZZQ
 *@date:2021/8/1
 */
data class SearchBean(var code : Int, var data : MutableList<Data>, var msg: String) {

    data class Data(
        var Title : String,
        var PictUrl : String,
        var ReservePrice : String
    )
}