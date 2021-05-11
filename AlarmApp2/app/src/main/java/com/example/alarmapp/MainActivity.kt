package com.example.alarmapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val set_alarm_button = findViewById<Button>(R.id.set_alarm_button)
        set_alarm_button.setOnClickListener {
            Toast.makeText(this, "10초 뒤에 알람이 울립니다", Toast.LENGTH_SHORT).show()

            val alarmService = AlarmService()
            startService(
                Intent(this, alarmService::class.java)
            )
        }
    }
}