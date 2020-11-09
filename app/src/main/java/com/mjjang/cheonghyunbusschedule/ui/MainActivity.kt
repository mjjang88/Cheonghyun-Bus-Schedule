package com.mjjang.cheonghyunbusschedule.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.mjjang.cheonghyunbusschedule.R
import com.mjjang.cheonghyunbusschedule.databinding.ActivityMainBinding
import com.mjjang.cheonghyunbusschedule.util.VersionManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        VersionManager.doVersionCheck(this)
    }
}