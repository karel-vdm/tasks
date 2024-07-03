package com.ordercloud.virginactivetasks.domain.usecase

import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.data.repository.TaskRepository

class UpdateTaskUseCase(
    private val repository: TaskRepository,
) {
    suspend fun execute(task: TaskEntity) {
        return repository.update(task)
    }
}