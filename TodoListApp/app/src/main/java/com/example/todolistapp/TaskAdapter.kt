package com.example.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by seheelee on 2021-05-18.
 */

class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    val tasks = mutableListOf<Task>()
    class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkBox: CheckBox = view.findViewById(R.id.item_task_check)
        val nameText: TextView = view.findViewById(R.id.item_task_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]

        holder.nameText.apply {
            text = task.name
            setTextColor(
                ContextCompat.getColor(
                    holder.nameText.context,
                    if (task.done) R.color.gray else R.color.black
                )
            )
        }

        holder.checkBox.apply {
            isChecked = task.done
            setOnClickListener {
                task.done = holder.checkBox.isChecked
                notifyItemChanged(position)
            }
        }

        holder.itemView.setOnLongClickListener {
            tasks.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, tasks.size)
            true
        }
    }

    override fun getItemCount(): Int = tasks.size

}