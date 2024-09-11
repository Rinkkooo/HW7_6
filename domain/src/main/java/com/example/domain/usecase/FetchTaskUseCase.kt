package com.example.domain.usecase

import com.example.domain.model.TaskEntityModel
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class FetchTaskUseCase(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(): Flow<List<TaskEntityModel>> {
        return taskRepository.fetchTask()
    }
}