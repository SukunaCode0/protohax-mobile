package com.protohax.client

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class MenuService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        ensureFg()
        try { System.loadLibrary("modmenu") } catch (_: UnsatisfiedLinkError) {}
        NativeBridge.init(this)
    }

    private fun ensureFg() {
        val id = "protohax-menu"
        if (Build.VERSION.SDK_INT >= 26) {
            val ch = NotificationChannel(id, "Menu", NotificationManager.IMPORTANCE_MIN)
            (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(ch)
            val n = Notification.Builder(this, id).setContentTitle("ProtohaxMobile menu running")
                .setSmallIcon(android.R.drawable.ic_dialog_info).build()
            startForeground(1, n)
        }
    }

    override fun onDestroy() {
        NativeBridge.shutdown()
        super.onDestroy()
    }
}

object NativeBridge {
    external fun init(service: Service)
    external fun shutdown()
    external fun getSupportedVersions(): String
}
