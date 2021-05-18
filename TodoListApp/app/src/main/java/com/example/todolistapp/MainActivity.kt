package com.example.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val taskAdapter = TaskAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        task_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = taskAdapter
        }

        new_task_button.setOnClickListener {
            startActivityForResult(
                Intent(this, NewTaskActivity::class.java),
                1234
            )
        }

//        taskAdapter.tasks.addAll(
//            listOf(
//                Task("첫 번째 할일"),
//                Task("두 번째 할일", true)
//            )
//        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("activity result")
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            val taskName = data?.getStringExtra("task_name")
            println("task name get")
            taskName?.let {
                println("get $it")
                taskAdapter.tasks.add(
                    Task(it)
                )
                taskAdapter.notifyItemInserted(
                    taskAdapter.tasks.lastIndex
                )
            }
        }
    }
}