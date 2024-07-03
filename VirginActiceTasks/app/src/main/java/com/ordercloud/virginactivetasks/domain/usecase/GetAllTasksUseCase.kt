package com.ordercloud.virginactivetasks.domain.usecase

import com.ordercloud.virginactivetasks.data.repository.TaskRepository
import com.ordercloud.virginactivetasks.domain.model.Task
import com.ordercloud.virginactivetasks.domain.model.transformer.TaskTransformer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllTasksUseCase(
    private val repository: TaskRepository,
) {
    suspend fun execute(): Flow<List<Task>> {
        return repository.getAll().map { tasks ->
            TaskTransformer.transform(tasks)
                .sortedWith(compareBy({ it.isCompleted }, { it.dueDate }))
        }
    }
}