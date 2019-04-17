package com.example.module_weather.mvp.presenter

import com.example.module_base.BaseObserver
import com.example.module_weather.bean.WeatherResultBean
import com.example.module_weather.mvp.contract.WeatherContract
import com.example.module_weather.mvp.model.WeatherModel
import com.example.module_weather.net.HttpUtils

class WeatherPresenter(view:WeatherContract.IView) : WeatherContract.IPresenter() {

    init {
        iView = view
        iModel = WeatherModel()
    }

    fun getWeatherData(){

        HttpUtils.getWeatherDataForCity("北京",object : BaseObserver<WeatherResultBean>(this){
            override fun onNext(t: WeatherResultBean) {
                super.onNext(t)
                iView!!.setDataToView(t)
            }
        })
    }

}