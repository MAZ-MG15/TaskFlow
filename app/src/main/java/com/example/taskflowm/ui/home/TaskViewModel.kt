package com.example.taskflowm.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.example.taskflowm.data.model.Task
import com.example.taskflowm.data.repository.TaskRepository
import com.example.taskflowm.util.NotificationHelper
import com.example.taskflowm.util.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository,
    private val notificationHelper: NotificationHelper,
    private val preferencesManager: PreferencesManager
) : ViewModel() {
    private val _userId = preferencesManager.userToken.asLiveData()
    val tasks = _userId.switchMap { userId ->
        if (userId != null) {
            repository.getTasksForUser(userId).asLiveData()
        } else {
            MutableLiveData(emptyList())
        }
    }

    fun addTask(title: String, description: String, dueDate: Long? = null) {
        viewModelScope.launch {
            val userId = preferencesManager.userToken.first() ?: return@launch
            val task = Task(userId = userId, title = title, description = description, dueDate = dueDate)
            val id = repository.insertTask(task)
            dueDate?.let {
                if (it > System.currentTimeMillis()) {
                    notificationHelper.scheduleTaskReminder(id.toInt(), title, description, it)
                }
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
            if (task.dueDate != null && task.dueDate > System.currentTimeMillis() && !task.isCompleted) {
                notificationHelper.scheduleTaskReminder(task.id, task.title, task.description, task.dueDate)
            } else {
                notificationHelper.cancelTaskReminder(task.id)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            notificationHelper.cancelTaskReminder(task.id)
        }
    }
}
