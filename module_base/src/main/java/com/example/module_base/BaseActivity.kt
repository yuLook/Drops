package com.example.module_base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lookplayer.base.BaseModel
import com.example.lookplayer.base.BasePresenter
import com.example.module_base.base.BasePresenter2
import com.example.module_base.base.BaseView
import com.example.module_base.utils.recyclerview.BaseBean

abstract  class BaseActivity<V: BaseView,P: BasePresenter<V>> : AppCompatActivity() {

    lateinit var presenter : P
    var dates = ArrayList<BaseBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getResLayoutId())
        ARouter.getInstance().inject(this)
        presenter = initPresenter()
        presenter.bindView(this as V)
    }

    /**设置布局id*/
    abstract fun getResLayoutId():Int

    /**初始化Presenter*/
    abstract fun initPresenter() : P

    /**注销页面时让P层与V层解绑，避免内存泄漏*/
    override fun onDestroy() {
        super.onDestroy()
        presenter!!.unBindView()
        (presenter as BasePresenter2<BaseView,BaseModel>).dispose()
    }

}