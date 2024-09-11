package com.example.hw7_6.di

import com.example.hw7_6.presentation.ui.fragment.taskCreate.TaskCreateViewModel
import com.example.hw7_6.presentation.ui.fragment.taskDetail.TaskDetailViewModel
import com.example.hw7_6.presentation.ui.fragment.taskList.TaskListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { TaskCreateViewModel(get()) }
    viewModel { TaskListViewModel(get(), get(), get()) }
    viewModel { TaskDetailViewModel(get()) }
}