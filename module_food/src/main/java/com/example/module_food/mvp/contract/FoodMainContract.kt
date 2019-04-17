package com.example.module_food.mvp.contract

import com.example.lookplayer.base.BaseModel
import com.example.module_base.base.BasePresenter2
import com.example.module_base.base.BaseView
import com.example.module_food.bean.FoodItemBean

interface FoodMainContract {

    interface IView : BaseView{
        fun setData(data:List<FoodItemBean>)
    }

    interface IModel : BaseModel{
        fun getData()
    }

    abstract class IPresenter : BasePresenter2<IView,IModel>()

}