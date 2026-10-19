package com.example.composeapp003.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.composeapp003.core.data.local.entity.TaskEntity

interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getTasks(): LiveData<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE task = :task")
    fun getTask(task: String): List<TaskEntity>

    @Insert
    fun addTask(task: TaskEntity)

    @Insert
    fun addTasks(tasks:List<TaskEntity>)

    @Query("DELETE FROM tasks WHERE task = :task")
    fun deleteTask(task: String)

    @Delete
    fun deleteTask(task: TaskEntity)

    @Delete
    fun deleteTasks(tasks: List<TaskEntity>)

    @Update
    fun updateTasks(tasks: List<TaskEntity>)
}