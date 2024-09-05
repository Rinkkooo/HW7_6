package com.example.domain

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(taskId: Int) {
        taskRepository.deleteTask(taskId)
    }
}