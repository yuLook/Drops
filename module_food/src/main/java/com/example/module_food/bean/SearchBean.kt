package com.example.module_food.bean

import com.example.module_base.utils.recyclerview.BaseBean

class SearchBean(layoutId : Int) : BaseBean() {

    var searchContent = ""

    private val mLayoutId = layoutId

    override fun getViewType(): Int {
        return mLayoutId
    }

}