package com.jrosario.d04052022.spendingtracer.data.repository

import com.jrosario.d04052022.spendingtracer.data.local.dao.SpendingDao
import com.jrosario.d04052022.spendingtracer.data.model.toDomain
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.domain.model.toEntity
import com.jrosario.d04052022.spendingtracer.domain.repository.SpendingRepository

class SpendingRepositoryImpl(
    private val spendingDao: SpendingDao
): SpendingRepository {
    override suspend fun getAll(): List<Spending> {
        return spendingDao.getAll().map { it.toDomain() }
    }

    override suspend fun insert(spending: Spending) {
        spendingDao.insert(spending.toEntity())
    }

    override suspend fun delete(spending: Spending) {
        spendingDao.delete(spending.toEntity())
    }
}