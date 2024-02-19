package com.heixss.spendings.feature.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SpendingEntity::class, CategoryEntity::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun spendingDao(): SpendingDao
    abstract fun categoryDao(): CategoryDao
}
