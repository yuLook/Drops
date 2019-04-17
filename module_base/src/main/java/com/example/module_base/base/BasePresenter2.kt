package com.example.module_base.base

import com.example.lookplayer.base.BaseModel
import com.example.lookplayer.base.BasePresenter
import io.reactivex.disposables.Disposable

abstract class BasePresenter2<V : BaseView,M : BaseModel> : BasePresenter<V>() {

    var iModel : M?=null
    var iView: V?=null

    private var disposables = ArrayList<Disposable>()

    fun setDisposable(disposable: Disposable){
        disposables.add(disposable)
    }

    fun removeDisposable(disposable: Disposable){
        disposables.remove(disposable)
    }

    /**关闭网络请求*/
    fun dispose(){
        for(d in disposables){
            if(!d.isDisposed){
                d.dispose()
            }
        }
    }

}