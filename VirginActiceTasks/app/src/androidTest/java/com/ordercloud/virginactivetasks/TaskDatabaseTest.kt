package com.ordercloud.virginactivetasks

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.data.source.room.TaskDao
import com.ordercloud.virginactivetasks.data.source.room.TaskDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException
import java.util.UUID

@RunWith(AndroidJUnit4::class)
class TaskDatabaseTest {
    private lateinit var taskDao: TaskDao
    private lateinit var db: TaskDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TaskDatabase::class.java
        ).build()
        taskDao = db.taskDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun create_task_returns_test() = runBlocking {
        val id = UUID.randomUUID()
        val task = TestUtil.getMockTask(id)
        taskDao.create(task)
    }

    @Test
    fun create_task_test() = runBlocking {
        val id = UUID.randomUUID()
        val task = TestUtil.getMockTask(id)
        taskDao.create(task)

        taskDao.getById(id).collect { createdTask ->
            assertEquals(task.id, createdTask.id)
            assertEquals(task.title, createdTask.title)
            assertEquals(task.description, createdTask.description)
            assertEquals(task.dueDate, createdTask.dueDate)
            assertEquals(task.isCompleted, createdTask.isCompleted)
        }
    }

    @Test
    fun get_all_tasks_test() = runBlocking {
        val taskCount = 40
        for (i in 1..taskCount) {
            val id = UUID.randomUUID()
            val task = TestUtil.getMockTask(id)
            taskDao.create(task)
        }

        taskDao.getAll().collect { tasks ->
            assertEquals(tasks.count(), taskCount)
        }
    }

    @Test
    fun update_task_test() = runBlocking {
        val id = UUID.randomUUID()
        val task = TestUtil.getMockTask(id)
        taskDao.create(task)

        val updatedTask = TestUtil.getUpdatedMockTask(id)
        taskDao.update(updatedTask)

        taskDao.getById(id).collect { createdTask ->
            assertEquals(updatedTask.id, createdTask.id)
            assertEquals(updatedTask.title, createdTask.title)
            assertEquals(updatedTask.description, createdTask.description)
            assertEquals(updatedTask.dueDate, createdTask.dueDate)
            assertEquals(updatedTask.isCompleted, createdTask.isCompleted)
        }
    }

    @Test
    fun delete_task_test() = runBlocking {
        val id = UUID.randomUUID()
        val task = TestUtil.getMockTask(id)
        taskDao.create(task)

        taskDao.delete(task)

        taskDao.getById(id).collect { task ->
            task.id
        }
    }
}