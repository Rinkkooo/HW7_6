package com.example.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.TaskEntityDto

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntityDto)

    @Query("SELECT * FROM task_table WHERE taskId = :taskId")
    suspend fun getTaskById(taskId: Int): TaskEntityDto?

    @Query("DELETE FROM task_table WHERE taskId = :taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("SELECT * FROM task_table")
    suspend fun fetchTasks(): List<TaskEntityDto>
}