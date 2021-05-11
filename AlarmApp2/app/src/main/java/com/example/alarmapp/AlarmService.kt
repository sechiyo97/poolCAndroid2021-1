package com.example.alarmapp

import android.app.*
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.PendingIntent.getBroadcast
import android.content.Intent
import android.os.Build
import android.os.IBinder
import java.util.*

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
        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val sender = getBroadcast(
            this,
            0,
            Intent(this, AlarmReceiver::class.java),
            FLAG_UPDATE_CURRENT
        )

        val currentTime = Calendar.getInstance().timeInMillis
        val triggerTime = currentTime + 10 * 1000
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime , sender)
//        sendBroadcast(
//            Intent(this, AlarmReceiver::class.java)
//        )
        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_ID = 1
    }
}