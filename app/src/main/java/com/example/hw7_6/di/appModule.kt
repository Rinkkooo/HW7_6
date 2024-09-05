package com.example.hw7_6.di

import com.example.domain.UseCase
import com.example.hw7_6.ui.fragment.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { TaskViewModel(get()) }
    single { UseCase(get()) }
    includes(dataModule)
}