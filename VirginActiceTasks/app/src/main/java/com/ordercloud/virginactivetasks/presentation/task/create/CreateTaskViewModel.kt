package com.ordercloud.virginactivetasks.presentation.task.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordercloud.virginactivetasks.data.model.TaskEntity
import com.ordercloud.virginactivetasks.domain.model.Task
import com.ordercloud.virginactivetasks.domain.usecase.CreateTaskUseCase
import com.ordercloud.virginactivetasks.domain.usecase.GetDateAsTimeStampUseCase
import com.ordercloud.virginactivetasks.presentation.model.TaskModel
import com.ordercloud.virginactivetasks.presentation.model.transformer.TaskModelTransformer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CreateTaskViewModel(
    private val createTaskUseCase: CreateTaskUseCase,
) : ViewModel() {

    companion object {
        private const val TAG = "CreateTaskViewModel"
    }

    private val _uiState = MutableStateFlow<TaskViewState>(TaskViewState.Loading(true))
    val uiState: StateFlow<TaskViewState> = _uiState

    fun onCreateTaskSelected(task: TaskModel) {
        createTask(task)
    }

    private fun createTask(task: TaskModel) {
        viewModelScope.launch {
            try {
                val transformedTask = TaskModelTransformer.transform(task)
                createTaskUseCase.execute(transformedTask)
                _uiState.value = TaskViewState.Success
            } catch (exception: Exception) {
                _uiState.value = TaskViewState.Error("Error creating task")
            }
        }
    }
}

sealed class TaskViewState {
    data class Loading(val isLoading: Boolean) : TaskViewState()
    data object Success : TaskViewState()
    data class Error(val errorMessage: String) : TaskViewState()
}