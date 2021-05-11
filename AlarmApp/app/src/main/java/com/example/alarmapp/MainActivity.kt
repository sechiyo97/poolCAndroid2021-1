package com.example.alarmapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private var year: Int? = null
    private var month: Int? = null
    private var day: Int? = null
    private var hour: Int? = null
    private var minute: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        set_alarm_btn.setOnClickListener {
            Toast.makeText(this, "$year/$month/$day $hour:$minute 에 알람이 울립니다.", Toast.LENGTH_SHORT).show()

            val alarmCalendar = Calendar.getInstance()
            alarmCalendar.set(
                year?:0, month?:0, day?:0, hour?:0, minute?:0, 0
            )

            val intent = Intent(this, AlarmService::class.java)
            intent.putExtra("alarmTime", alarmCalendar.timeInMillis)
            startService(intent)
        }

        clear_alarm_btn.setOnClickListener {
            stopService(
                Intent(this, AlarmService::class.java)
            )
        }

        text_time.setOnClickListener {
            val currentCalendar = Calendar.getInstance()
            TimePickerDialog(
                this,
                { _, hour, minute ->
                   setSelectedTime(hour, minute)
                },
                currentCalendar.get(Calendar.HOUR_OF_DAY),
                currentCalendar.get(Calendar.MINUTE),
                false
            ).show()
        }

        text_date.setOnClickListener {
            val currentCalendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                { _, year, month, day ->
                    setSelectedDate(year, month, day)
                },
                currentCalendar.get(Calendar.YEAR),
                currentCalendar.get(Calendar.MONTH),
                currentCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        checkPermission()
    }

    private fun setSelectedTime(hour: Int, minute: Int) {
        this.hour = hour
        this.minute = minute

        text_time.text = "$hour:$minute"
    }

    private fun setSelectedDate(year: Int, month: Int, day: Int) {
        this.year = year
        this.month = month
        this.day = day

        text_date.text = "$year/$month/$day"
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + this.packageName)
                )
                startActivityForResult(intent, 1)
            } else {
                //Permission Granted-System will work
            }
        }
    }
}