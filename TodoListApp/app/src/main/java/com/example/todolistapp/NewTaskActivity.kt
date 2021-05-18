package com.example.todolistapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_task.*

/**
 * Created by seheelee on 2021-05-18.
 */

class NewTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        new_task_finish_button.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("task_name", new_task_name.text.toString())
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}