package com.example.taskflowm.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskflowm.data.model.Task
import com.example.taskflowm.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    val tasks = repository.getAllTasks().asLiveData()

    fun addTask(title: String, description: String, dueDate: Long? = null) {
        viewModelScope.launch {
            repository.insertTask(Task(title = title, description = description, dueDate = dueDate))
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}
