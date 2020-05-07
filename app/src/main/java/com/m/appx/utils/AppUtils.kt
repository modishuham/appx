package com.m.appx.utils

import android.content.Context
import android.widget.Toast
import com.m.appx.base.MyApplication

object AppUtils {
    var context: Context? = null

    init {
        context = MyApplication.instance.applicationContext
    }

    fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}