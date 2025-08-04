package com.jrosario.d04052022.spendingtracer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jrosario.d04052022.spendingtracer.data.local.dao.SpendingDao
import com.jrosario.d04052022.spendingtracer.data.model.SpendingEntity

@Database(entities = [SpendingEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun spendingDao(): SpendingDao
}