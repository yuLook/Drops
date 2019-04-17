package com.example.module_weather.net

import com.example.module_weather.bean.WeatherResultBean
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object HttpUtils {

    private var service : ApiService

    init {
         var client : OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    if (request.method() == "GET"){
                        //添加公共请求参数
                        var url = request.url()
                        var newUrl = url.newBuilder()
                            .addEncodedQueryParameter("key","b846d6320c08ae2b376c37c87c1ac79a")
                            .build()
                        request = request.newBuilder().url(newUrl).build()
                    }
                    return chain.proceed(request)
                }

            })
            .build()

         var retrofit : Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://v.juhe.cn")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)

    }

    fun getWeatherData(lon:Double,lat:Double,subscriber: Observer<WeatherResultBean>){
        service.getWeatherData(lon, lat)
            .subscribeOn(Schedulers.io())//IO线程加载数据
            .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
            .subscribe(subscriber)
    }

    fun getWeatherDataForCity(cityName:String,subscriber: Observer<WeatherResultBean>){
        service.getWeatherData(cityName)
            .subscribeOn(Schedulers.io())//IO线程加载数据
            .observeOn(AndroidSchedulers.mainThread())//主线程显示数据
            .subscribe(subscriber)

    }

}