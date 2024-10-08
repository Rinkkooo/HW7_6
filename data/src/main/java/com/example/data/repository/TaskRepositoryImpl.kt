package com.example.data.repository

import com.example.data.db.dao.TaskDao
import com.example.data.model.fromDomain
import com.example.data.model.toDomain
import com.example.domain.model.TaskEntityModel
import com.example.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {

    override suspend fun insertTask(taskEntityModel: TaskEntityModel) {
        taskDao.insertTask(taskEntityModel.fromDomain())
    }

    override suspend fun getTaskById(taskId: Int): TaskEntityModel {
        return taskDao.getTaskById(taskId)!!.toDomain()
    }

    override suspend fun deleteTask(taskId: Int) {
        taskDao.deleteTask(taskId)
    }

    override suspend fun fetchTask(): Flow<List<TaskEntityModel>> {
        return taskDao.fetchTasks().map { taskList ->
            taskList.map { it.toDomain() }
        }
    }


}