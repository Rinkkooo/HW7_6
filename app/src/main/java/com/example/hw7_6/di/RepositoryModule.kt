package com.example.hw7_6.di

import org.koin.dsl.module

val repositoryModule = module {
    single<Repository> {
        TaskRepositoryImpl(get())
    }
}