package com.example.module_base.utils

import android.widget.Toast
import com.example.module_base.BaseApplication

/**
 * Toast单例工具
 * */
class ToastUtils {

    companion object {
        private var toast = Toast.makeText(BaseApplication.instance,"",Toast.LENGTH_SHORT)
        var instance = ToastUtils()
    }

    fun show(string: String){
        toast.setText(string)
        toast.show()
    }

}