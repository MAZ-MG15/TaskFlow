package com.example.taskflowm

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskflowm.databinding.ActivityCalendarBinding
import com.example.taskflowm.databinding.DialogAddTaskBinding
import com.example.taskflowm.ui.home.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@AndroidEntryPoint
class CalendarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarBinding
    private val taskViewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter
    
    private val monthYearFormatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    private val selectedDateFormatter = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
    private val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    
    private var selectedDateCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        setupCalendar()
        setupBottomNav()
        observeTasks()
        
        updateDateHeaders()
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter(
            tasks = emptyList(),
            onTaskChecked = { task, isChecked ->
                taskViewModel.updateTask(task.copy(isCompleted = isChecked))
            },
            onEditClick = { _ ->
                // Edit logic if needed
            },
            onDeleteClick = { task ->
                taskViewModel.deleteTask(task)
            }
        )
        binding.calendarTasksRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.calendarTasksRecyclerView.adapter = adapter
    }

    private fun setupCalendar() {
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            selectedDateCalendar.set(year, month, dayOfMonth)
            updateDateHeaders()
            filterTasksForSelectedDate()
        }
    }

    private fun updateDateHeaders() {
        binding.currentMonthYear.text = monthYearFormatter.format(selectedDateCalendar.time)
        binding.selectedDateText.text = selectedDateFormatter.format(selectedDateCalendar.time)
    }

    private fun observeTasks() {
        taskViewModel.tasks.observe(this) { 
            filterTasksForSelectedDate()
        }
    }

    private fun filterTasksForSelectedDate() {
        val allTasks = taskViewModel.tasks.value ?: return
        
        val filteredTasks = allTasks.filter { task ->
            task.dueDate?.let { dueDate ->
                val taskCal = Calendar.getInstance()
                taskCal.timeInMillis = dueDate
                taskCal.get(Calendar.YEAR) == selectedDateCalendar.get(Calendar.YEAR) &&
                taskCal.get(Calendar.DAY_OF_YEAR) == selectedDateCalendar.get(Calendar.DAY_OF_YEAR)
            } ?: false
        }
        
        adapter.updateTasks(filteredTasks)
    }

    private fun setupBottomNav() {
        binding.addTaskFab.setOnClickListener {
            showAddTaskDialog()
        }

        binding.navTasks.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(0, 0)
            finish()
        }
        
        binding.navProfile.setOnClickListener {
            startActivity(Intent(this, UserInfoActivity::class.java))
            overridePendingTransition(0, 0)
            finish()
        }
    }

    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(LayoutInflater.from(this))
        var selectedDate: Long? = selectedDateCalendar.timeInMillis // Default to currently selected calendar date

        dialogBinding.selectDateButton.text = dateFormatter.format(selectedDate)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        dialogBinding.selectDateButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            selectedDate?.let { calendar.timeInMillis = it }
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
}
