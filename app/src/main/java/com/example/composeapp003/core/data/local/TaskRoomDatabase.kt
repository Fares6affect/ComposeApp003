package com.example.composeapp003.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.composeapp003.Task
import com.example.composeapp003.core.data.local.dao.TaskDao

@Database(entities = [(Task::class)], version = 1)
abstract class TaskRoomDatabase: RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object{
        private var INSTANCE: TaskRoomDatabase? = null
        fun getInstance(context: Context): TaskRoomDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskRoomDatabase::class.java,
                        "Task_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}