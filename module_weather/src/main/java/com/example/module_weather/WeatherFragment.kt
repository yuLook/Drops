package com.example.module_weather

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_base.BaseObserver
import com.example.module_base.utils.ConstantRouterPath
import com.example.module_base.utils.ToastUtils
import com.example.module_weather.bean.WeatherResultBean
import com.example.module_weather.net.HttpUtils
import com.google.gson.Gson
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_weather.*

open class WeatherFragment : Fragment(){

    private var weatherResultBean : String =""

    companion object {
        val instance = WeatherFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        cl_weather.setOnClickListener {

            var bundle = Bundle()
            bundle.putString("bundle",weatherResultBean)

            ARouter.getInstance().build(ConstantRouterPath.weather_activity)
                .withString("json",weatherResultBean)
                .navigation(activity)
        }

        HttpUtils.getWeatherDataForCity("北京", object : BaseObserver<WeatherResultBean>(null) {
            override fun onNext(value: WeatherResultBean) {
                super.onNext(value)
                weatherResultBean = Gson().toJson(value)

                tv_tem.text=value.result.sk.temp+"℃"
                tv_address.text=value.result.today.city
                tv_suggest.text=value.result.today.dressing_advice
                tv_weather.text=value.result.today.temperature+" "+value.result.today.weather+" "+value.result.sk.wind_direction
            }
        })

    }

}