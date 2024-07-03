package com.ordercloud.virginactivetasks.domain.model.transformer

import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.domain.model.Task
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object TaskTransformer {

    fun transform(tasks: List<TaskEntity>): List<Task> {
        return tasks.map { task ->
            transform(task)
        }
    }

    private fun transform(task: TaskEntity) = Task(
        id = task.id,
        title = task.title,
        description = task.description,
        dueDate = task.dueDate,
        isCompleted = task.isCompleted,
    )

    fun transform(task: Task) = TaskEntity(
        id = task.id,
        title = task.title,
        description = task.description,
        dueDate = task.dueDate,
        isCompleted = task.isCompleted,
    )
}