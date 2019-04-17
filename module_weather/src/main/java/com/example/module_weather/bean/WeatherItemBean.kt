package com.example.module_weather.bean

import com.example.module_base.utils.recyclerview.BaseBean
import com.example.module_weather.R

/**
 * 未来几天天气的item
 * */
class WeatherItemBean : BaseBean() {

    override fun getViewType(): Int {
        return R.layout.item_weather_bean
    }

    /**时间*/
    var time : String = "today"
    /**温度*/
    var temp : String = "0℃"
    /**天气状况*/
    var weather : String = "晴"

}