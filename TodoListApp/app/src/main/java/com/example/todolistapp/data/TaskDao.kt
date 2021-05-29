package com.example.todolistapp.data

import androidx.room.*

/**
 * Created by seheelee on 2021-05-18.
 */

@Dao
interface TaskDao {
    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update(entity = Task::class)
    fun update(task: Task)

    @Query("SELECT * FROM task")
    fun getAll() : List<Task>
}