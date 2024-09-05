package com.example.data

import com.example.domain.Task
import com.example.domain.UseCase

class TaskRepositoryImpl(private val taskDao: TaskDao) : UseCase {
    override suspend fun getTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: Task) {
        TODO("Not yet implemented")
    }


}