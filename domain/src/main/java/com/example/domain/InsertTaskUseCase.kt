package com.example.domain

class InsertTaskUseCase(private val taskRepository: TaskRepository) {
    suspend fun execute(task: Task) {
        taskRepository.insertTask(task)
    }
}