package com.example.hw7_6.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.DeleteTaskUseCase
import com.example.domain.GetTaskUseCase
import com.example.domain.InsertTaskUseCase
import com.example.domain.Task
import kotlinx.coroutines.launch


class TaskViewModel(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    fun insertTask(task: Task) {
        viewModelScope.launch {
            insertTaskUseCase.execute(task)
        }
    }

    fun getTaskById(taskId: Int) {
        viewModelScope.launch {
            getTaskUseCase.execute(taskId)
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            deleteTaskUseCase.execute(taskId)
        }
    }
}