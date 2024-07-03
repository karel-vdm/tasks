package com.ordercloud.virginactivetasks.data.source

import com.ordercloud.virginactivetasks.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface TaskSource {

    suspend fun create(task: TaskEntity)

    fun getById(id: UUID): Flow<TaskEntity>

    fun getAll(): Flow<List<TaskEntity>>

    suspend fun update(task: TaskEntity)

    suspend fun delete(task: TaskEntity)

}