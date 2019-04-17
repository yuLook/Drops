package com.example.module_weather.bean

import android.animation.ValueAnimator
import com.example.module_base.utils.recyclerview.BaseBean
import com.example.module_base.view.WeatherProgressView
import com.example.module_weather.R
import java.lang.ref.WeakReference

class WeatherProgressBean : BaseBean() ,ValueAnimator.AnimatorUpdateListener{

    var progress:Int=0
    lateinit var view:WeakReference<WeatherProgressView>

    override fun getViewType(): Int {
        return R.layout.item_weather_progress
    }

    fun startAnimator(view:WeatherProgressView){
        this.view = WeakReference(view)
        this.view.get()!!.setStepMax(100)
        this.view.get()!!.setCurrentStep(1)
        var animator= ValueAnimator.ofFloat(0F, progress.toFloat())
        animator.addUpdateListener(this)
        animator.duration = 2000
        animator.start()
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        val currentStep = animation!!.animatedValue as Float
        view.let{
            view.get()!!.setCurrentStep(currentStep.toInt())
        }
    }

}