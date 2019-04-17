package com.example.module_weather.mvp.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.module_base.BaseActivity
import com.example.module_base.BaseAdapterKotlin
import com.example.module_base.utils.ConstantRouterPath
import com.example.module_base.utils.recyclerview.BaseBean
import com.example.module_base.utils.recyclerview.RViewHolder
import com.example.module_base.view.WeatherProgressView
import com.example.module_weather.R
import com.example.module_weather.bean.WeatherResultBean
import com.example.module_weather.bean.WeatherHeadItemBean
import com.example.module_weather.bean.WeatherItemBean
import com.example.module_weather.bean.WeatherProgressBean
import com.example.module_weather.mvp.contract.WeatherContract
import com.example.module_weather.mvp.presenter.WeatherPresenter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_weather.*

@Route(path = ConstantRouterPath.weather_activity)
class WeatherActivity : BaseActivity<WeatherContract.IView,WeatherPresenter>(),WeatherContract.IView {


    @Autowired(name="json")
    lateinit var json:String

    private lateinit var adapter : BaseAdapterKotlin
    private var headItem : WeatherHeadItemBean = WeatherHeadItemBean()
    private var resultBean : WeatherResultBean ?=null

    override fun getResLayoutId(): Int {
        return R.layout.activity_weather
    }

    override fun initPresenter(): WeatherPresenter {
        return WeatherPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(this)
        dates.add(headItem)
        adapter = object : BaseAdapterKotlin(this,dates){
            override fun bindData(holder: RViewHolder, item: BaseBean) {
                when{
                    item.getViewType()==R.layout.item_weather_head ->{
                        holder.setText(R.id.tv_tmp,headItem.tmp)
                    }
                    item.getViewType()==R.layout.item_weather_bean ->{
                        holder.setText(R.id.tv_temp,(item as WeatherItemBean).temp)
                        holder.setText(R.id.tv_weather,item.weather)
                        holder.setText(R.id.tv_time,item.time)
                    }
                    item.getViewType()==R.layout.item_weather_progress ->{
                        (item as WeatherProgressBean).startAnimator(holder.getView(R.id.progress_weather))
                    }
                }

            }
        }
        recycler_view.adapter = adapter

        if(json.length>10){
            var resultBean = Gson().fromJson(json,WeatherResultBean::class.java)
            setDataToView(resultBean)
        }else{
            presenter.getWeatherData()
        }

    }

    override fun setDataToView(resultBean: WeatherResultBean) {
        this.resultBean=resultBean

        headItem.tmp = resultBean.result.sk.temp+"℃"

        for((key,bean) in resultBean.result.future){
            var item = WeatherItemBean()
            item.weather=bean.weather
            item.time=bean.week
            item.temp=bean.temperature
            adapter.add(item)
        }

        for (i in 0..4){
            var item = WeatherProgressBean()
            item.progress=50+i
            adapter.add(item)
        }

        adapter.notifyDataSetChanged()

    }

}