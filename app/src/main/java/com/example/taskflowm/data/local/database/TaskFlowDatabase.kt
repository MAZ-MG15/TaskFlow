package com.example.taskflowm.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskflowm.data.local.dao.TaskDao
import com.example.taskflowm.data.local.dao.UserDao
import com.example.taskflowm.data.model.Task
import com.example.taskflowm.data.model.User

@Database(entities = [Task::class, User::class], version = 2, exportSchema = false)
abstract class TaskFlowDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
}
