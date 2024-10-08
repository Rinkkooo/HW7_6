package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.model.TaskEntityModel

@Entity(tableName = "task_table")
data class TaskEntityDto(
    @PrimaryKey(autoGenerate = true) val taskId: Int = 0,
    val taskName: String,
    val taskDesc: String,
    val time: Long,
)

fun TaskEntityDto.toDomain() = TaskEntityModel(taskId, taskName, taskDesc, time )
fun TaskEntityModel.fromDomain() = TaskEntityDto(taskId, taskName, taskDesc, time)