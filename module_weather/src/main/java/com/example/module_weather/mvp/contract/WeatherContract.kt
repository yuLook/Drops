package com.example.module_weather.mvp.contract

import com.example.lookplayer.base.BaseModel
import com.example.module_base.base.BasePresenter2
import com.example.module_base.base.BaseView
import com.example.module_weather.bean.WeatherResultBean

interface WeatherContract {

    interface IView : BaseView{
        fun setDataToView(resultBean: WeatherResultBean)
    }

    interface IModel : BaseModel

    abstract class IPresenter : BasePresenter2<IView,IModel>()

}