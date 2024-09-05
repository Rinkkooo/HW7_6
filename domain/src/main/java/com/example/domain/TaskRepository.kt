package com.example.domain

interface TaskRepository {
    suspend fun insertTask(task: Task)
    suspend fun getTaskById(taskId: Int): Task?
    suspend fun deleteTask(taskId: Int)
}