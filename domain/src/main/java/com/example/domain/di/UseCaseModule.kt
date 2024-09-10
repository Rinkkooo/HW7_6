package com.example.domain.di

import com.example.domain.usecase.DeleteTaskUseCase
import com.example.domain.usecase.FetchTaskUseCase
import com.example.domain.usecase.GetTaskUseCase
import com.example.domain.usecase.InsertTaskUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { InsertTaskUseCase(get()) }
    factory { GetTaskUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
    factory { FetchTaskUseCase(get()) }
}