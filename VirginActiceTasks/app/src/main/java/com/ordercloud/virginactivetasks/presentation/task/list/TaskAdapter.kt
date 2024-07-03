package com.ordercloud.virginactivetasks.presentation.task.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ordercloud.virginactivetasks.databinding.ListItemTaskBinding
import com.ordercloud.virginactivetasks.presentation.model.TaskDiffCallback
import com.ordercloud.virginactivetasks.presentation.model.TaskModel

class TaskAdapter(
    private val onItemSelected: (TaskModel) -> Unit,
) : ListAdapter<TaskModel, TaskViewHolder>(TaskDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ListItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding, onItemSelected)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val selectedItem = getItem(position)
        holder.bind(selectedItem)
    }
}