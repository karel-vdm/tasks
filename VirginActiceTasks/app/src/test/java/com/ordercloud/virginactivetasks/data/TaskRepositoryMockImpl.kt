package com.ordercloud.virginactivetasks.data

import com.ordercloud.virginactivetasks.TestUtil
import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID

class TaskRepositoryMockImpl : TaskRepository {
    override suspend fun create(task: TaskEntity) {
        // do nothing
    }

    override suspend fun getById(id: UUID): Flow<TaskEntity> {
        return flow {
            emit(TestUtil.getMockTask(id))
        }
    }

    override suspend fun getAll(): Flow<List<TaskEntity>> {
        val tasks = mutableListOf<TaskEntity>()
        return flow {
            val taskCount = 40
            for (i in 1..taskCount) {
                val task = TestUtil.getMockTask(UUID.randomUUID())
                tasks.add(task)
            }
            emit(tasks)
        }
    }

    override suspend fun update(task: TaskEntity) {
        // do nothing
    }

    override suspend fun delete(task: TaskEntity) {
        // do nothing
    }

}