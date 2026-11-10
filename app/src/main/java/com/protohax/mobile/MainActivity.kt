package com.protohax.mobile
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
class MainActivity : ComponentActivity() {
  override fun onCreate(s: Bundle?) {
    super.onCreate(s)
    setContent {
      MaterialTheme(colorScheme = darkColorScheme()) {
        Surface(Modifier.fillMaxSize()) {
          Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text("ProtohaxMobile 0.1.0 — MITM relay, MC 1.26+", style = MaterialTheme.typography.headlineSmall)
            Text("1) Grant overlay  2) Start menu  3) Point MC at local relay. No root, no memory patch.")
            var status by remember { mutableStateOf("idle") }
            Button(onClick = {
              startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName)))
            }) { Text("Grant overlay permission") }
            Button(onClick = { startForegroundService(Intent(this@MainActivity, MenuService::class.java)); status = "menu service started" }) { Text("Start menu service") }
            Text(status)
          }
        }
      }
    }
  }
}
