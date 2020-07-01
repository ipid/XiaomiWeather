package me.ipid.android.xiaomiweather

import android.app.Application
import android.content.Context

lateinit var publicContext: Context

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        publicContext = applicationContext
    }
}
