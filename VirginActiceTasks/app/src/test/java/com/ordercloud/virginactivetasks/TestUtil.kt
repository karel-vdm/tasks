package com.ordercloud.virginactivetasks

import com.ordercloud.virginactivetasks.data.model.TaskEntity
import java.util.UUID

object TestUtil {

    fun getMockTask(id: UUID): TaskEntity {
        return TaskEntity(
            id = id,
            title = "Test Task",
            description = "This is a test task",
            dueDate = System.currentTimeMillis(),
            isCompleted = true
        )
    }

    fun getUpdatedMockTask(id: UUID): TaskEntity {
        return TaskEntity(
            id = id,
            title = "Updated Task",
            description = "This is a test task",
            dueDate = System.currentTimeMillis(),
            isCompleted = true
        )
    }
}