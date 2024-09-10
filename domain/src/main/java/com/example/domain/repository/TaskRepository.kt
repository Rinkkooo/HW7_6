package com.example.domain.repository

import com.example.domain.model.TaskEntityModel

interface TaskRepository {

    suspend fun insertTask(taskEntityModel: TaskEntityModel)
    suspend fun getTaskById(taskId: Int): TaskEntityModel?
    suspend fun deleteTask(taskId: Int)
    suspend fun fetchTask(): List<TaskEntityModel>
}