package com.ordercloud.virginactivetasks.presentation.model

import androidx.recyclerview.widget.DiffUtil
import java.util.UUID

data class TaskModel(
    val id: UUID = UUID.randomUUID(),
    val title: String = String(),
    val description: String = String(),
    val dueDate: String = String(),
    val isCompleted: Boolean = false,
)

object TaskDiffCallback : DiffUtil.ItemCallback<TaskModel>() {
    override fun areItemsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TaskModel, newItem: TaskModel): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.title == newItem.title
    }
}