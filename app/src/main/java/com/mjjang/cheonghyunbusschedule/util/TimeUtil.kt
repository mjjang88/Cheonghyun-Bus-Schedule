package com.mjjang.cheonghyunbusschedule.util

import java.text.SimpleDateFormat

object TimeUtil {

    fun calcTimeGap(time01: String, time02: String): Int{
        val simpleDataFormat = SimpleDateFormat("HH:mm")
        val date01 = simpleDataFormat.parse(time01)
        val date02 = simpleDataFormat.parse(time02)

        return ((date01.time - date02.time) / (1000 * 60)).toInt()
    }
}