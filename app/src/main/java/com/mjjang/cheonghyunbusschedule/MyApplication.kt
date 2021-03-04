package com.mjjang.cheonghyunbusschedule

import android.app.Application
import com.mjjang.cheonghyunbusschedule.util.RemoteConfigManager

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        RemoteConfigManager.init()
    }
}