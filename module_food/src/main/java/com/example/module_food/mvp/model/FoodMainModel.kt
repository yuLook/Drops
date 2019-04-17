package com.example.module_food.mvp.model

import com.example.module_food.bean.FoodItemBean
import com.example.module_food.mvp.contract.FoodMainContract

class FoodMainModel(listener : OnFoodMainCallBack) : FoodMainContract.IModel{

    var onFoodMainCallBack = listener

    /**获取菜谱列表数据*/
    override fun getData() {
        var dates = ArrayList<FoodItemBean>()
        for (num in 0..5){
            var item = FoodItemBean()
            item.foodName = "红烧肉$num"
            item.foodTrait = "猪肉 大葱 大料 肥而不腻"
            item.foodImg = "http://img4.imgtn.bdimg.com/it/u=2292702129,4248006659&fm=26&gp=0.jpg"
            dates.add(item)
        }
        onFoodMainCallBack.onFoodListResult(dates)
    }

    open interface OnFoodMainCallBack{

        fun onFoodListResult(dates:List<FoodItemBean>)

    }



}