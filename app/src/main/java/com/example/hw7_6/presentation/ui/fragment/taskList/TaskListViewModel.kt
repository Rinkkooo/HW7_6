package com.example.hw7_6.presentation.ui.fragment.taskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.DeleteTaskUseCase
import com.example.domain.usecase.FetchTaskUseCase
import com.example.domain.usecase.GetTaskUseCase
import kotlinx.coroutines.launch

class TaskListViewModel (
    private val getTaskUseCase: GetTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val fetchTaskUseCase: FetchTaskUseCase
) : ViewModel() {

    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            getTaskUseCase(taskId)
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            deleteTaskUseCase(taskId)
        }
    }

    suspend fun fetchTasks() = fetchTaskUseCase()
}