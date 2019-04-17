package com.example.module_food.mvp.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_base.BaseActivity
import com.example.module_base.BaseAdapterKotlin
import com.example.module_base.utils.ConstantRouterPath
import com.example.module_base.utils.recyclerview.BaseBean
import com.example.module_base.utils.recyclerview.RViewHolder
import com.example.module_food.R
import com.example.module_food.bean.FoodItemBean
import com.example.module_food.bean.SearchBean
import com.example.module_food.mvp.contract.FoodMainContract
import com.example.module_food.mvp.presenter.FoodMainPresenter
import kotlinx.android.synthetic.main.activity_food.*

@Route(path = ConstantRouterPath.food_list_activity)
class FoodListActivity : BaseActivity<FoodMainContract.IView,FoodMainPresenter>(),FoodMainContract.IView {

    private lateinit var adapter : BaseAdapterKotlin

    override fun getResLayoutId(): Int {
        return R.layout.activity_food
    }

    override fun initPresenter(): FoodMainPresenter {
        return FoodMainPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        presenter!!.getFoodListData()
    }

    private fun initView(){

        var searchBean =  SearchBean(R.layout.item_search)
        searchBean.searchContent = "search"
        dates.add(searchBean)

        recycler_view.layoutManager = LinearLayoutManager(this)

        adapter = object : BaseAdapterKotlin(this,dates){

            override fun bindData(holder: RViewHolder, item: BaseBean) {

                if (item.getViewType()==R.layout.item_search){
                    holder.setEditText(R.id.et_search,(item as SearchBean).searchContent)
                }else if (item.getViewType()==R.layout.item_food){
                    holder.setText(R.id.tv_food_name,(item as FoodItemBean).foodName)
                }
            }
        }

        recycler_view.adapter = adapter

    }

    override fun setData(data:List<FoodItemBean>) {
        adapter.addAll(data)
    }

}