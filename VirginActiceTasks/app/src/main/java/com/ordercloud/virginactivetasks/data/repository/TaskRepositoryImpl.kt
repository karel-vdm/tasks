package com.ordercloud.virginactivetasks.data.repository

import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.data.source.TaskSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class TaskRepositoryImpl(
    private val source: TaskSource,
) : TaskRepository {

    override suspend fun create(task: TaskEntity) {
        return source.create(task)
    }

    override suspend fun getById(id: UUID): Flow<TaskEntity> {
        return source.getById(id)
    }

    override suspend fun getAll(): Flow<List<TaskEntity>> {
        return source.getAll()
    }

    override suspend fun update(task: TaskEntity) {
        return source.update(task)
    }

    override suspend fun delete(task: TaskEntity) {
        return source.delete(task)
    }

}