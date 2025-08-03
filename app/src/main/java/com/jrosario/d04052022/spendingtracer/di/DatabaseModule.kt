package com.jrosario.d04052022.spendingtracer.di

import android.content.Context
import androidx.room.Room
import com.jrosario.d04052022.spendingtracer.data.local.dao.SpendingDao
import com.jrosario.d04052022.spendingtracer.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "spending_db")
            .build()
    }

    @Provides
    fun provideSpendingDao(db: AppDatabase): SpendingDao = db.spendingDao()
}