package com.example.composeapp003.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
class TaskEntity {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "taskId")
    var id:Int = 0
    var task: String? = null
    var day:String? = null
    var time:String? = null
    var completed: Boolean = false

    constructor(){}
    constructor(task: String, day: String, time: String, completed: Boolean){
        this.task = task
        this.day = day
        this.time = time
        this.completed = completed
    }
}

data class Task(
    var task: String = "",
    var day: String = "",
    var time: String = "",
    var completed: Boolean = false)
