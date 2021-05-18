package com.example.todolistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by seheelee on 2021-05-18.
 */

@Entity
data class Task (
    var name: String,
    var done: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)