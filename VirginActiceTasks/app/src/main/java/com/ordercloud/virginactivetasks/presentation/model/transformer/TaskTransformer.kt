package com.ordercloud.virginactivetasks.presentation.model.transformer

import com.ordercloud.virginactivetasks.domain.model.Task
import com.ordercloud.virginactivetasks.presentation.model.TaskModel

object TaskModelTransformer {

    fun transform(tasks: List<Task>): List<TaskModel> {
        return tasks.map { task ->
            transform(task)
        }
    }

    private fun transform(task: Task) = TaskModel(
        id = task.id,
        title = task.title,
        description = task.description,
        dueDate = task.dueDate,
        isCompleted = task.isCompleted,
    )

    fun transform(task: TaskModel) = Task(
        id = task.id,
        title = task.title,
        description = task.description,
        dueDate = task.dueDate,
        isCompleted = task.isCompleted,
    )

}
