package com.example.module_food.mvp.presenter

import com.example.module_food.bean.FoodItemBean
import com.example.module_food.mvp.contract.FoodMainContract
import com.example.module_food.mvp.model.FoodMainModel

class FoodMainPresenter(view : FoodMainContract.IView) : FoodMainContract.IPresenter() ,FoodMainModel.OnFoodMainCallBack{

    init {
        iView=view
        iModel=FoodMainModel(this)
    }

    fun getFoodListData(){
        iModel!!.getData()
    }

    override fun onFoodListResult(dates:List<FoodItemBean>) {
        iView!!.setData(dates)
    }

}