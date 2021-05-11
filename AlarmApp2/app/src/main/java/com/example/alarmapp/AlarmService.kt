package com.example.alarmapp

import android.app.*
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent.getBroadcast
import android.content.Intent
import android.os.Build
import android.os.IBinder

/**
 * Created by seheelee on 2021-05-11.
 */

class AlarmService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        // Oreo (Android 8) 이상 버전에 대응
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "some channel",
                IMPORTANCE_DEFAULT
            )

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            val notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("알람 제목")
                .setContentText("알람 내용")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()

            startForeground(NOTIFICATION_ID, notification)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendBroadcast(
            Intent(this, AlarmReceiver::class.java)
        )
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 1
    }
}