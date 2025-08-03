package com.jrosario.d04052022.spendingtracer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrosario.d04052022.spendingtracer.data.model.AccountEntity
import com.jrosario.d04052022.spendingtracer.data.model.SpendingEntity

@Dao
interface SpendingDao {
    @Query("SELECT * FROM AccountEntity")
    suspend fun getAccountLog(): AccountEntity

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAccountLog(accountEntity: AccountEntity)

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertSpendingLog(spendingLog: SpendingEntity)

    @Query("SELECT * FROM SpendingEntity")
    suspend fun getSpendingLogs(): List<SpendingEntity>
}