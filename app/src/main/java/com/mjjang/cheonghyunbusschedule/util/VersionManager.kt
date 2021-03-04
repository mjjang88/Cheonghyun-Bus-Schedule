package com.mjjang.cheonghyunbusschedule.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig

object VersionManager {

    fun doVersionCheck(context: Context) {
        val remoteConfig = Firebase.remoteConfig
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    try {
                        val marketVersion = remoteConfig["app_version"].asString()
                        val deviceVersion = context.packageManager.getPackageInfo(context.packageName, 0).versionName

                        if (marketVersion > deviceVersion) {
                            showUpdateDialog(context)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
    }

    private fun showUpdateDialog(context: Context) {
        MaterialAlertDialogBuilder(context)
            .setTitle("업데이트 알림")
            .setMessage("신규 업데이트가 있습니다.\n플레이스토어로 이동합니다.")
            .setNeutralButton("나중에") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("이동") { dialog, which ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("market://details?id=" + context.packageName)
                context.startActivity(intent)
                dialog.dismiss()
            }
            .show()
    }
}