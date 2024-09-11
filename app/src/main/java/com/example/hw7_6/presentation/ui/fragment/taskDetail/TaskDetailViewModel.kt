package com.example.hw7_6.presentation.ui.fragment.taskDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.domain.model.TaskEntityModel
import com.example.domain.usecase.GetTaskUseCase
import kotlinx.coroutines.Dispatchers

class TaskDetailViewModel (
    private val getTaskUseCase: GetTaskUseCase
) : ViewModel() {

    fun getTaskById(taskId: Int) : LiveData<TaskEntityModel?> = liveData {
        viewModelScope.coroutineContext + Dispatchers.IO
        emit(getTaskUseCase(taskId))
    }
}