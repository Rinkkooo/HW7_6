package com.example.hw7_6.presentation.models

import com.example.domain.model.TaskEntityModel

data class TaskEntityUI (
    val taskId: Int,
    val taskName: String,
    val taskDesc: String,
    val time: Int
)

fun TaskEntityModel.toUI() = TaskEntityUI(taskId, taskName, taskDesc, time)
fun TaskEntityUI.fromDomain() = TaskEntityModel(taskId, taskName, taskDesc, time)