package com.protohax.mobile
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
class MenuService : Service() {
  val session = GameSession()
  override fun onBind(i: Intent?): IBinder? = null
  override fun onCreate() {
    super.onCreate()
    val id = "protohax-menu"
    if (Build.VERSION.SDK_INT >= 26) {
      val ch = NotificationChannel(id, "Menu", NotificationManager.IMPORTANCE_MIN)
      (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(ch)
      startForeground(1, Notification.Builder(this, id).setContentTitle("ProtohaxMobile menu running").setSmallIcon(android.R.drawable.ic_dialog_info).build())
    }
    session.modules.apply { defaultModules().forEach { it.session = session; register(it) } }
  }
}
