package com.ordercloud.virginactivetasks.presentation.task.list

import androidx.recyclerview.widget.RecyclerView
import com.ordercloud.virginactivetasks.databinding.ListItemTaskBinding
import com.ordercloud.virginactivetasks.domain.model.Task
import com.ordercloud.virginactivetasks.presentation.model.TaskModel

class TaskViewHolder(
    private val binding: ListItemTaskBinding,
    private val onItemSelected: (TaskModel) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: TaskModel) {
        with(binding) {
            taskTitle.text = task.title
            taskDescription.text = task.description
            taskDueDate.text = task.dueDate
            taskCompletedCheckbox.isChecked = task.isCompleted
        }
    }
}