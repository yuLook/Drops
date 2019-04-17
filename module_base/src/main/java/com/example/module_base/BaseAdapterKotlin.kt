package com.example.module_base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.module_base.utils.recyclerview.BaseBean
import com.example.module_base.utils.recyclerview.RViewHolder

/**
 * Kotlin模式的adapter
 * */
abstract class BaseAdapterKotlin(context : Context, dataList:MutableList<BaseBean>) : RecyclerView.Adapter<RViewHolder>() {

    private var mContext = context
    private var mDataList = dataList
    private var layoutInflater = LayoutInflater.from(mContext)!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewHolder {
        var view = layoutInflater.inflate(viewType,parent,false)
        return RViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: RViewHolder, position: Int) {
        bindData(holder, mDataList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return mDataList[position].getViewType()
    }

    abstract fun bindData(holder: RViewHolder,item : BaseBean)

    fun addAll(dates : List<BaseBean>){
        this.mDataList.addAll(dates)
        notifyDataSetChanged()
    }

    fun add(data:BaseBean){
        this.mDataList.add(data)
    }

}