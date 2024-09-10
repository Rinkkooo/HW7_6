package com.example.hw7_6.presentation.models

import com.example.domain.model.TaskEntityModel

data class TaskEntityUI (
    val taskId: Int,
    val taskName: String
)

fun TaskEntityModel.toUI() = TaskEntityUI(taskId, taskName)
fun TaskEntityUI.fromDomain() = TaskEntityModel(taskId, taskName)