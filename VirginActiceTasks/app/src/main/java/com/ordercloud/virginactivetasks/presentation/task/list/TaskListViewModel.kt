package com.ordercloud.virginactivetasks.presentation.task.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ordercloud.virginactivetasks.domain.usecase.GetAllTasksUseCase
import com.ordercloud.virginactivetasks.presentation.model.TaskModel
import com.ordercloud.virginactivetasks.presentation.model.transformer.TaskModelTransformer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val getAllTasksUseCase: GetAllTasksUseCase,
) : ViewModel() {

    companion object {
        private const val TAG = "TaskListFragment"
    }

    private val _uiState = MutableStateFlow<TaskViewState>(TaskViewState.Loading(true))
    val uiState: StateFlow<TaskViewState> = _uiState

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        viewModelScope.launch {
            getAllTasksUseCase.execute()
                .flowOn(Dispatchers.IO)
                .collect { result ->
                    Log.d(TAG, result.toString())
                    val tasks = TaskModelTransformer.transform(result)
                    _uiState.value = TaskViewState.Success(tasks)
                }
        }
    }
}

sealed class TaskViewState {
    data class Loading(val isLoading: Boolean) : TaskViewState()
    data class Success(val tasks: List<TaskModel>) : TaskViewState()
    data class Error(val errorMessage: String) : TaskViewState()
}