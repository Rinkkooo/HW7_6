package com.example.hw7_6.presentation.ui.fragment.taskList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.DeleteTaskUseCase
import com.example.domain.usecase.FetchTaskUseCase
import com.example.domain.usecase.GetTaskUseCase
import com.example.hw7_6.presentation.models.TaskEntityUI
import com.example.hw7_6.presentation.models.toUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class TaskListViewModel(
    private val getTaskUseCase: GetTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val fetchTaskUseCase: FetchTaskUseCase
) : ViewModel() {

    private val _taskFlow = MutableStateFlow<List<TaskEntityUI>>(emptyList())
    val taskFlow: Flow<List<TaskEntityUI>> = _taskFlow

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

    fun fetchTasks() {
        viewModelScope.launch {
            fetchTaskUseCase().collect { domainModel ->
                _taskFlow.value = domainModel.map {
                    it.toUI()
                }
            }
        }
    }

    private fun filterTasks(
        tasks: List<TaskEntityUI>,
        filter: (TaskEntityUI) -> Boolean
    ): List<TaskEntityUI> {
        return tasks.filter(filter)
    }

    fun fetchFilteredTasks(filter: (TaskEntityUI) -> Boolean): Flow<List<TaskEntityUI>> {
        return taskFlow.map { tasks ->
            filterTasks(tasks, filter)
        }
    }

    fun sortTasksByTime(
        tasks: List<TaskEntityUI>,
    ): List<TaskEntityUI> {
        return tasks.sortedBy { it.time }
    }
}