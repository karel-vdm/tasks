package com.ordercloud.virginactivetasks.data.source.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.data.source.TaskSource
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface TaskDao : TaskSource {

    @Insert
    override suspend fun create(task: TaskEntity)

    @Query("SELECT * FROM tasks WHERE id = :id")
    override fun getById(id: UUID): Flow<TaskEntity>

    @Query("SELECT * FROM tasks")
    override fun getAll(): Flow<List<TaskEntity>>

    @Update
    override suspend fun update(task: TaskEntity)

    @Delete
    override suspend fun delete(task: TaskEntity)
}