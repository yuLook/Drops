package com.example.module_base

import com.example.lookplayer.base.BaseModel
import com.example.module_base.base.BasePresenter2
import com.example.module_base.base.BaseView
import com.example.module_base.utils.ToastUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

open class BaseObserver<T> (p : Any?): Observer<T> {

    private var d : Disposable ?=null
    private var presenter : BasePresenter2<BaseView,BaseModel> ?=null

    init {
        if(p!=null){
            presenter  = p as BasePresenter2<BaseView, BaseModel>
        }
    }

    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        this.d=d
        presenter?.let {
            presenter!!.setDisposable(d)
        }
    }

    override fun onNext(t: T) {
        presenter?.let {
           presenter!!.removeDisposable(d!!)
        }
    }

    override fun onError(e: Throwable) {
        ToastUtils.instance.show("请求失败")
        presenter?.let {
            presenter!!.removeDisposable(d!!)
        }
    }

}