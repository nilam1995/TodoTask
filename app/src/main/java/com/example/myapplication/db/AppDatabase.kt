package com.example.myapplication.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.model.Task

@Database(entities = arrayOf( Task::class), version = 1, exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

    abstract fun appDao(): TodoDao
}