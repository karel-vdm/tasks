package com.ordercloud.virginactivetasks.domain.model

import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    val title: String = String(),
    val description: String = String(),
    val dueDate: String = String(),
    val isCompleted: Boolean = false,
)