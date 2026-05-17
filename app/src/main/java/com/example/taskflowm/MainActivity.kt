package com.example.taskflowm

import android.Manifest
import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskflowm.data.model.Task
import com.example.taskflowm.databinding.ActivityMainBinding
import com.example.taskflowm.databinding.DialogAddTaskBinding
import com.example.taskflowm.databinding.DialogEditTaskBinding
import com.example.taskflowm.ui.home.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter
    private val taskViewModel: TaskViewModel by viewModels()
    private val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notifications enabled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Notifications disabled", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        checkNotificationPermission()
        setupRecyclerView()
        setupBottomNav()
        setupMenu()
        observeTasks()
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(
            tasks = emptyList(),
            onTaskChecked = { task, isChecked ->
                taskViewModel.updateTask(task.copy(isCompleted = isChecked))
            },
            onEditClick = { task ->
                showEditTaskDialog(task)
            },
            onDeleteClick = { task ->
                taskViewModel.deleteTask(task)
            }
        )
        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tasksRecyclerView.adapter = adapter
    }

    private fun observeTasks() {
        taskViewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }
    }

    private fun showEditTaskDialog(task: Task) {
        val dialogBinding = DialogEditTaskBinding.inflate(LayoutInflater.from(this))
        dialogBinding.editTaskTitle.setText(task.title)
        dialogBinding.editTaskDesc.setText(task.description)
        
        var selectedDate: Long? = task.dueDate
        selectedDate?.let {
            dialogBinding.editSelectDateButton.text = dateFormatter.format(it)
        }

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.editSelectDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            selectedDate?.let { calendar.timeInMillis = it }
            
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val newCalendar = Calendar.getInstance()
                newCalendar.set(year, month, dayOfMonth)
                selectedDate = newCalendar.timeInMillis
                dialogBinding.editSelectDateButton.text = dateFormatter.format(selectedDate)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        dialogBinding.updateTaskButton.setOnClickListener {
            val title = dialogBinding.editTaskTitle.text.toString()
            val desc = dialogBinding.editTaskDesc.text.toString()
            if (title.isNotEmpty()) {
                taskViewModel.updateTask(task.copy(title = title, description = desc, dueDate = selectedDate))
                dialog.dismiss()
                Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun setupBottomNav() {
        binding.addTaskFab.setOnClickListener {
            showAddTaskDialog()
        }
        
        binding.navSchedule.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.navFocus.setOnClickListener {
            startActivity(Intent(this, FocusActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.navProfile.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
            overridePendingTransition(0, 0)
        }
    }

    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(LayoutInflater.from(this))
        var selectedDate: Long? = null

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.selectDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                val newCalendar = Calendar.getInstance()
                newCalendar.set(year, month, dayOfMonth)
                selectedDate = newCalendar.timeInMillis
                dialogBinding.selectDateButton.text = dateFormatter.format(selectedDate)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        dialogBinding.saveTaskButton.setOnClickListener {
            val title = dialogBinding.taskTitleEditText.text.toString()
            if (title.isNotEmpty()) {
                taskViewModel.addTask(
                    title, 
                    dialogBinding.taskDescEditText.text.toString(),
                    selectedDate
                )
                dialog.dismiss()
                Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Title cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }

    private fun setupMenu() {
        binding.dashboardProfileImage.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
            overridePendingTransition(0, 0)
        }

        binding.settingsIcon.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add(0, 1, 0, "Developer Info")
            popup.menu.add(0, 2, 1, getString(R.string.sign_out))

            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    1 -> {
                        startActivity(Intent(this, DevInfoActivity::class.java))
                        true
                    }
                    2 -> {
                        startActivity(Intent(this, SignInActivity::class.java))
                        finishAffinity()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}
