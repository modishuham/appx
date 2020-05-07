package com.m.appx.base

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
    }

    companion object {
        lateinit var instance: MyApplication
            private set
    }

}