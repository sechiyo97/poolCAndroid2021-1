package com.example.todolistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.data.Task
import com.example.todolistapp.data.TaskDao
import com.example.todolistapp.data.TaskDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val taskAdapter = TaskAdapter()
    private var taskDB : TaskDatabase ?= null
    private var taskDao : TaskDao ?= null
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

        taskDB = TaskDatabase.getDatabase(this)
        taskDao = taskDB?.taskDao()

        taskDB?.queryExecutor?.execute {
            taskDao?.getAll()?.let {
                taskAdapter.tasks.addAll(it)
                taskAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            val taskName = data?.getStringExtra("task_name")
            taskName?.let {
                val task = Task(it)
                taskAdapter.tasks.add(task)
                taskAdapter.notifyItemInserted(
                    taskAdapter.tasks.lastIndex
                )
                taskDB?.queryExecutor?.execute {
                    taskDao?.insert(task)
                }
            }
        }
    }
}