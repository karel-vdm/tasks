package com.ordercloud.virginactivetasks.data.repository

import com.ordercloud.virginactivetasks.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface TaskRepository {

    suspend fun create(task: TaskEntity)

    suspend fun getById(id: UUID): Flow<TaskEntity>

    suspend fun getAll(): Flow<List<TaskEntity>>

    suspend fun update(task: TaskEntity)

    suspend fun delete(task: TaskEntity)

}

