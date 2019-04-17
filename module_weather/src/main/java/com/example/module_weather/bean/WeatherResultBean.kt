package com.example.module_weather.bean

class WeatherResultBean {

    var resultcode = "0"
    var reason = "error"
    var error_code = -1
    lateinit var result : Result

    open class Result{
        lateinit var sk : Sk
        lateinit var today : Today
        lateinit var future : LinkedHashMap<String,Day>
    }

    open class Sk{
        lateinit var temp : String
        lateinit var wind_direction : String
        lateinit var wind_strength : String
        lateinit var humidity : String
        lateinit var time : String
    }

    open class Today{
        lateinit var temperature : String
        lateinit var weather : String
        lateinit var wind : String
        lateinit var week : String
        lateinit var city : String
        lateinit var date_y : String
        lateinit var dressing_index : String
        lateinit var dressing_advice : String
        lateinit var uv_index : String
        lateinit var comfort_index : String
        lateinit var wash_index : String
        lateinit var travel_index : String
        lateinit var exercise_index : String
        lateinit var drying_index : String
    }

    open class Day{
        lateinit var temperature : String
        lateinit var weather : String
        lateinit var wind : String
        lateinit var week : String
        lateinit var date : String
    }

}