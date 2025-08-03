package com.jrosario.d04052022.spendingtracer.di

import com.jrosario.d04052022.spendingtracer.data.local.dao.SpendingDao
import com.jrosario.d04052022.spendingtracer.data.repository.SpendingRepositoryImpl
import com.jrosario.d04052022.spendingtracer.domain.repository.SpendingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSpendingRepository(spendingDao: SpendingDao): SpendingRepository =
        SpendingRepositoryImpl(spendingDao)
}