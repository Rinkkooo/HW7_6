package com.example.hw7_6.ui.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Task
import com.example.domain.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun getTask() {
        viewModelScope.launch {
            val tasks = taskRepository.getTasks()
        }

        fun insertTask(task: Task) {
            viewModelScope.launch {
                taskRepository.insertTask(task)
            }
        }
    }

}