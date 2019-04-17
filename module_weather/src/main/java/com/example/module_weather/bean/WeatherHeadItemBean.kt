package com.example.module_weather.bean

import com.example.module_base.utils.recyclerview.BaseBean
import com.example.module_weather.R

class WeatherHeadItemBean : BaseBean(){

    override fun getViewType(): Int {
        return R.layout.item_weather_head
    }

    var tmp : String = "0℃"

}