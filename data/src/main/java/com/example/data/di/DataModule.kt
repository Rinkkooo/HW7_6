package com.example.data.di

import androidx.room.Room
import com.example.data.db.AppDatabase
import org.koin.dsl.module

val dataModules = listOf(
    databaseModule, repositoryModule
)
