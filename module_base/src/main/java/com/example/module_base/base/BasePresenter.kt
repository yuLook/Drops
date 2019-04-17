package com.example.lookplayer.base

import com.example.module_base.base.BaseView
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseView> {

    var view :WeakReference<V>?=null

    fun bindView(view : V){
        this.view=WeakReference(view)
    }

    private fun isBind() : Boolean{
        return view!=null &&view!!.get()!=null
    }

    fun  unBindView(){
        if (isBind()){
            view!!.clear()
            view=null
        }
    }

    fun obtainView():V?{
        return if (isBind()) view!!.get() else null
    }



}