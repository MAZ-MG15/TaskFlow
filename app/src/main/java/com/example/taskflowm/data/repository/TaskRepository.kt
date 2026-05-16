package com.example.taskflowm.data.repository

import com.example.taskflowm.data.local.dao.TaskDao
import com.example.taskflowm.data.model.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun getAllTasks() = taskDao.getAllTasks()
    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
}
