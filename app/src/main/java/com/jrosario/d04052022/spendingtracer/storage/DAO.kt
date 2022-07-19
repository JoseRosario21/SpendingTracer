package com.jrosario.d04052022.spendingtracer.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrosario.d04052022.spendingtracer.models.AccountLog
import com.jrosario.d04052022.spendingtracer.models.SpendingLog

@Dao
interface DAO {
    @Query("SELECT * FROM AccountLog")
    suspend fun getAccountLog(): AccountLog

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccountLog(accountLog: AccountLog)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpendingLog(spendingLog: SpendingLog)

    @Query("SELECT * FROM SpendingLog")
    suspend fun getSpendingLogs(): List<SpendingLog>
}