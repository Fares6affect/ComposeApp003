package com.example.composeapp003.core.data.local

import androidx.lifecycle.LiveData
import com.example.composeapp003.core.data.local.dao.TaskDao
import com.example.composeapp003.core.data.local.entity.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val userList: LiveData<List<Task>>? = taskDao.getTasks()

    fun addTask(task: Task){
        coroutineScope.launch(Dispatchers.IO){
            taskDao.addTask(task)
        }
    }

    fun deleteTask(task: Task){
        coroutineScope.launch(Dispatchers.IO){
            taskDao.deleteTask(task)
        }
    }

}