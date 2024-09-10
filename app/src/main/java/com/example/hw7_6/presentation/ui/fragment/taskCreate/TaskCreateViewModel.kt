package com.example.hw7_6.presentation.ui.fragment.taskCreate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.InsertTaskUseCase
import com.example.hw7_6.presentation.models.TaskEntityUI
import com.example.hw7_6.presentation.models.fromDomain
import kotlinx.coroutines.launch


class TaskCreateViewModel(
    private val insertTaskUseCase: InsertTaskUseCase,

) : ViewModel() {

    fun insertTask(task: TaskEntityUI) {
        viewModelScope.launch {
            insertTaskUseCase(task.fromDomain())
        }
    }

}