package com.example.domain.usecase

import com.example.domain.model.TaskEntityModel
import com.example.domain.repository.TaskRepository

class FetchTaskUseCase(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(): List<TaskEntityModel> {
        return taskRepository.fetchTask()
    }
}