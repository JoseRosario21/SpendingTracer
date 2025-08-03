package com.jrosario.d04052022.spendingtracer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrosario.d04052022.spendingtracer.data.model.AccountEntity

@Dao
interface AccountDao {
    @Query("SELECT * FROM AccountEntity")
    suspend fun getAccountLog(): AccountEntity

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAccountLog(accountEntity: AccountEntity)

}