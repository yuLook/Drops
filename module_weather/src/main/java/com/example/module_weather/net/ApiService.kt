package com.example.module_weather.net

import com.example.module_weather.bean.WeatherResultBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    /**
     * 获取天气信息 经纬度
     * */
    @GET("/weather/geo")
    fun getWeatherData(@Query("lon")lon:Double, @Query("lat")lat:Double): Observable<WeatherResultBean>

    /**
     * 获取天气信息 城市
     * */
    @GET("/weather/index")
    fun getWeatherData(@Query("cityname")cityName:String):Observable<WeatherResultBean>

}