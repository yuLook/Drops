package com.example.module_food.bean

import com.example.module_base.utils.recyclerview.BaseBean
import com.example.module_food.R

class FoodItemBean : BaseBean() {

    override fun getViewType(): Int {
        return R.layout.item_food
    }

    var foodName : String =""
    var foodTrait : String =""
    var foodImg : String = ""

}