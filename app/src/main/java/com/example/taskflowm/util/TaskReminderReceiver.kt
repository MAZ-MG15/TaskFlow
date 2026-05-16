package com.example.taskflowm.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskReminderReceiver : BroadcastReceiver() {

    @Inject
    lateinit var notificationHelper: NotificationHelper

    override fun onReceive(context: Context, intent: Intent) {
        val taskTitle = intent.getStringExtra("TASK_TITLE") ?: "Task Reminder"
        val taskDesc = intent.getStringExtra("TASK_DESC") ?: "You have a task due now!"
        val taskId = intent.getIntExtra("TASK_ID", 0)
        
        notificationHelper.showNotification(taskTitle, taskDesc, taskId)
    }
}
