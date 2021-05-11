package com.example.alarmapp

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_alarm.*

/**
 * Created by seheelee on 2021-05-11.
 */

class AlarmActivity : AppCompatActivity() {
    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        playMusic()

        stop_alarm_button.setOnClickListener {
            mediaPlayer.stop()
        }
    }

    private fun playMusic() {
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        mediaPlayer = MediaPlayer.create(this, alarmSound)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
    }
}