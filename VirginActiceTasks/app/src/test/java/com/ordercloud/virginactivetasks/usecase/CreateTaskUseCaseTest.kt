package com.ordercloud.virginactivetasks.usecase

import com.ordercloud.virginactivetasks.data.TaskRepositoryMockImpl
import com.ordercloud.virginactivetasks.TestUtil
import com.ordercloud.virginactivetasks.domain.usecase.CreateTaskUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.UUID

class CreateTaskUseCaseTest {
    private val createTaskUseCase = CreateTaskUseCase(
        repository = TaskRepositoryMockImpl()
    )

    @Test
    fun create_task_test() = runBlocking {
        val task = TestUtil.getMockTask(UUID.randomUUID())
        createTaskUseCase.execute(task)
    }
}

