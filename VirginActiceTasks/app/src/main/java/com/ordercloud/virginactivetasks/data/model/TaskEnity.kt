package com.ordercloud.virginactivetasks.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = false) val id: UUID = UUID.randomUUID(),
    val title: String = String(),
    val description: String = String(),
    val dueDate: String = String(),
    val isCompleted: Boolean = false,
)