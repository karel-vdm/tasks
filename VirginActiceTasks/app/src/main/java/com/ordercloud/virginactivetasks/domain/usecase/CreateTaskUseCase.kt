package com.ordercloud.virginactivetasks.domain.usecase

import com.ordercloud.virginactivetasks.data.repository.TaskRepository
import com.ordercloud.virginactivetasks.domain.model.Task
import com.ordercloud.virginactivetasks.domain.model.transformer.TaskTransformer

class CreateTaskUseCase(
    private val repository: TaskRepository,
) {
    suspend fun execute(task: Task) {
        val transformedTask = TaskTransformer.transform(task)
        return repository.create(transformedTask)
    }
}