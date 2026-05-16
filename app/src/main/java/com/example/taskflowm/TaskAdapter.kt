package com.example.taskflowm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskflowm.data.model.Task
import com.example.taskflowm.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter(
    private var tasks: List<Task>,
    private val onTaskChecked: (Task, Boolean) -> Unit,
    private val onEditClick: (Task) -> Unit,
    private val onDeleteClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val dateFormatter = SimpleDateFormat("dd MMM", Locale.getDefault())

    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.taskTitleTextView.text = task.title
        holder.binding.taskCheckbox.setOnCheckedChangeListener(null)
        holder.binding.taskCheckbox.isChecked = task.isCompleted
        
        if (task.dueDate != null) {
            holder.binding.taskDateTextView.visibility = View.VISIBLE
            holder.binding.taskDateTextView.text = dateFormatter.format(task.dueDate)
        } else {
            holder.binding.taskDateTextView.visibility = View.GONE
        }

        holder.binding.taskCheckbox.setOnCheckedChangeListener { _, isChecked ->
            onTaskChecked(task, isChecked)
        }

        holder.binding.editIconButton.setOnClickListener {
            onEditClick(task)
        }

        holder.binding.deleteIconButton.setOnClickListener {
            onDeleteClick(task)
        }
    }

    override fun getItemCount() = tasks.size

    fun updateTasks(newTasks: List<Task>) {
        this.tasks = newTasks
        notifyDataSetChanged()
    }
}
