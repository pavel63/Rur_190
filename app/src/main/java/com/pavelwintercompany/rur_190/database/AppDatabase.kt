package com.pavelwintercompany.rur_190.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pavelwintercompany.rur_190.entity.HourModel

@Database(entities = arrayOf(HourModel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hourModelDao(): HourModelDao
}