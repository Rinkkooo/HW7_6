package com.example.domain.usecase

import com.example.domain.model.Task
import com.example.domain.model.TaskEntityModel
import com.example.domain.repository.TaskRepository

class GetTaskUseCase(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskId: Int): TaskEntityModel? {
        return taskRepository.getTaskById(taskId)
    }
}