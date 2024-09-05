package com.example.hw7_6.di

import androidx.room.Room
import com.example.data.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    single {get<AppDatabase>().taskDao()}
}