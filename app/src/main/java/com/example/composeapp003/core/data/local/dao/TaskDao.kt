package com.example.composeapp003.core.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.composeapp003.core.data.local.entity.Task

interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE task = :task")
    fun getTask(task: String): List<Task>

    @Insert
    fun addTask(task: Task)

    @Insert
    fun addTasks(tasks:List<Task>)

    @Query("DELETE FROM tasks WHERE task = :task")
    fun deleteTask(task: String)

    @Delete
    fun deleteTask(task: Task)

    @Delete
    fun deleteTasks(tasks: List<Task>)

    @Update
    fun updateTasks(tasks: List<Task>)
}