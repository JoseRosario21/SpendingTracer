package com.jrosario.d04052022.spendingtracer.data.repository

import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.domain.repository.SpendingRepository

class SpendingRepositoryImpl: SpendingRepository {
    override suspend fun getAll(): List<Spending> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(spending: Spending) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(spending: Spending) {
        TODO("Not yet implemented")
    }
}