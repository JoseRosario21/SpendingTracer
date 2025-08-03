package com.jrosario.d04052022.spendingtracer.domain.repository

import com.jrosario.d04052022.spendingtracer.domain.model.Spending

interface SpendingRepository {
    suspend fun getAll(): List<Spending>
    suspend fun insert(spending: Spending)
    suspend fun delete(spending: Spending)
}