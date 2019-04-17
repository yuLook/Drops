package com.example.drops

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.example.module_base.utils.ConstantRouterPath
import com.example.module_weather.WeatherFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 点滴
 * */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ARouter.getInstance().inject(this)

        setContentView(R.layout.activity_main)

        bt_food.setOnClickListener {
            ARouter.getInstance().build(ConstantRouterPath.food_list_activity).navigation(this)
        }

        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame_weather,WeatherFragment.instance,"fragmentWeather")
        transaction.commit()

    }

}
