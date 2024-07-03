package com.ordercloud.virginactivetasks.presentation.task.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.domain.model.Task
import com.ordercloud.virginactivetasks.domain.usecase.CreateTaskUseCase
import com.ordercloud.virginactivetasks.domain.usecase.DeleteTaskUseCase
import com.ordercloud.virginactivetasks.domain.usecase.GetAllTasksUseCase
import com.ordercloud.virginactivetasks.domain.usecase.UpdateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<TaskViewState>(TaskViewState.Loading(true))
    val uiState: StateFlow<TaskViewState> = _uiState

    init {
        getAllTasks()
    }

    fun onCreateTaskSelected(task: TaskEntity) {
        createTask(task)
    }

    private fun getAllTasks() {
        viewModelScope.launch {
            getAllTasksUseCase.execute()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    _uiState.value = TaskViewState.Success(result)
                }
        }
    }

    private fun createTask(task: TaskEntity) {
        viewModelScope.launch {
            try {

            } catch (exception: Exception) {

            }

        }
    }

    private fun deleteTask(task: TaskEntity) {
        viewModelScope.launch {
            try {
                deleteTaskUseCase.execute(task)
            } catch (exception: Exception) {

            }
        }
    }

    private fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            try {
                updateTaskUseCase.execute(task)
            } catch (exception: Exception) {

            }
        }
    }

}

sealed class TaskViewState {
    data class Loading(val isLoading: Boolean) : TaskViewState()
    data class Success(val tasks: List<Task>) : TaskViewState()
    data class Error(val errorMessage: String) : TaskViewState()
}