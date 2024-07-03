package com.ordercloud.virginactivetasks

import androidx.room.Room
import com.ordercloud.virginactivetasks.data.repository.TaskRepository
import com.ordercloud.virginactivetasks.data.repository.TaskRepositoryImpl
import com.ordercloud.virginactivetasks.data.source.room.TaskDatabase
import com.ordercloud.virginactivetasks.data.source.TaskSource
import com.ordercloud.virginactivetasks.domain.usecase.CreateTaskUseCase
import com.ordercloud.virginactivetasks.domain.usecase.DeleteTaskUseCase
import com.ordercloud.virginactivetasks.domain.usecase.GetAllTasksUseCase
import com.ordercloud.virginactivetasks.domain.usecase.UpdateTaskUseCase
import com.ordercloud.virginactivetasks.presentation.task.create.CreateTaskViewModel
import com.ordercloud.virginactivetasks.presentation.task.list.TaskListViewModel
import com.ordercloud.virginactivetasks.presentation.task.viewmodel.TaskViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val taskModule = module {

    single {
        Room.databaseBuilder(androidApplication(), TaskDatabase::class.java, "task_database")
            .build()
    }

    single { get<TaskDatabase>().taskDao() } bind TaskSource::class

    factory {
        TaskRepositoryImpl(
            source = get()
        )
    } bind TaskRepository::class

    factory {
        CreateTaskUseCase(repository = get())
    }

    factory {
        GetAllTasksUseCase(repository = get())
    }

    factory {
        DeleteTaskUseCase(repository = get())
    }

    factory {
        UpdateTaskUseCase(repository = get())
    }

    viewModel {
        TaskViewModel(
            createTaskUseCase = get(),
            getAllTasksUseCase = get(),
            deleteTaskUseCase = get(),
            updateTaskUseCase = get(),
        )
    }

    viewModel {
        TaskListViewModel(
            getAllTasksUseCase = get(),
        )
    }

    viewModel {
        CreateTaskViewModel(
            createTaskUseCase = get(),
        )
    }
}