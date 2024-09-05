package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun taskDao(): Dao
}