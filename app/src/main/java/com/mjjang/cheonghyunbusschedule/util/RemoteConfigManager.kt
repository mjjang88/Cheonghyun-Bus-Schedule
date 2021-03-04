package com.mjjang.cheonghyunbusschedule.util

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.mjjang.cheonghyunbusschedule.BuildConfig
import com.mjjang.cheonghyunbusschedule.R

object RemoteConfigManager {

    fun init() {
        val remoteConfig = Firebase.remoteConfig
        if (BuildConfig.DEBUG) {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 60 * 10
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        } else {
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 60 * 60 * 24
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        }
    }
}