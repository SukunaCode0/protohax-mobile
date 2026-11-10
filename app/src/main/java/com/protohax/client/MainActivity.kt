package com.protohax.client

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
        }
        val info = TextView(this).apply {
            text = "ProtohaxMobile v1\n\n1. Grant overlay permission\n2. Start menu service\n3. Open Minecraft Bedrock\n\nBuild: demo UI mode works without MC.\nInjected mode needs your own patched launcher or root injector (see docs/INJECTION.md)."
            textSize = 15f
        }
        val permBtn = Button(this).apply {
            text = "Grant overlay permission"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + packageName)))
            }
        }
        val startBtn = Button(this).apply {
            text = "Start menu service"
            setOnClickListener {
                startForegroundService(Intent(this@MainActivity, MenuService::class.java))
            }
        }
        layout.addView(info)
        layout.addView(permBtn)
        layout.addView(startBtn)
        setContentView(layout)
    }
}
