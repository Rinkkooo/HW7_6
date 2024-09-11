package com.example.domain.usecase

import com.example.domain.model.TaskEntityModel
import com.example.domain.repository.TaskRepository

class InsertTaskUseCase(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(task: TaskEntityModel) {
        taskRepository.insertTask(task)
    }
}