package com.jrosario.d04052022.spendingtracer.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jrosario.d04052022.spendingtracer.data.local.dao.SpendingDao
import com.jrosario.d04052022.spendingtracer.data.model.AccountEntity
import com.jrosario.d04052022.spendingtracer.data.model.SpendingEntity

@Database(entities = [AccountEntity::class, SpendingEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): SpendingDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!
            }
        }
    }
}