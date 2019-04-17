package com.example.module_base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_base.utils.LocationUtils

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initRoute()
        instance=this
    }

    companion object {
        var instance : Application?=null
    }

    private fun initRoute() {
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
    }

}