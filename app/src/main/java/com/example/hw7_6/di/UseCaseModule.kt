package com.example.hw7_6.di

import org.koin.dsl.module

val useCaseModule = module {
    factory { InsertTaskUseCase(get()) }
    factory { GetTaskUseCase(get()) }
    factory { DeleteTaskUseCase(get()) }
}