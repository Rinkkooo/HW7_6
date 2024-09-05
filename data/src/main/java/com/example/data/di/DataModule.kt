package com.example.data.di

import androidx.room.Room
import com.example.data.AppDatabase
import com.example.data.TaskRepositoryImpl
import com.example.domain.UseCase
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "task_database"
        ).build()
    }

    single { get<AppDatabase>().taskDao() }

    single<UseCase> { TaskRepositoryImpl(get()) }
}
