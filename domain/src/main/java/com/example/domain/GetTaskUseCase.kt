package com.example.domain

class GetTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(taskId: Int): Task? {
        return taskRepository.getTaskById(taskId)
    }
}