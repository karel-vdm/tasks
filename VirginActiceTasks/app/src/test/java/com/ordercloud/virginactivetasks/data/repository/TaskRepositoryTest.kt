package com.ordercloud.virginactivetasks.data.repository

import com.ordercloud.virginactivetasks.data.TaskSourceMockImpl
import com.ordercloud.virginactivetasks.TestUtil
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID

class TaskRepositoryTest {

    private val id: UUID = UUID.randomUUID()

    private val taskRepository = TaskRepositoryImpl(
        source = TaskSourceMockImpl()
    )

    @Test
    fun create_task_test() = runTest {
        val task = TestUtil.getMockTask(id)
        taskRepository.create(task)
    }

    @Test
    fun get_task_test() = runTest {
        taskRepository.getById(id).collect { task ->
            assertEquals(id, task.id)
        }
    }

    @Test
    fun get_all_tasks_test() = runTest {
        taskRepository.getAll().collect {
            it.count()
        }
    }

    @Test
    fun update_task_test() = runBlocking {
        val task = TestUtil.getUpdatedMockTask(id)
        taskRepository.update(task)
    }

    @Test
    fun delete_task_test() = runBlocking {
        val task = TestUtil.getMockTask(id)
        taskRepository.delete(task)
    }

}