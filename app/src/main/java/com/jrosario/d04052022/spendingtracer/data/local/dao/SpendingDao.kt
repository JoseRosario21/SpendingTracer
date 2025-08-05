package com.jrosario.d04052022.spendingtracer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jrosario.d04052022.spendingtracer.data.model.SpendingEntity

@Dao
interface SpendingDao {
    @Query("SELECT * FROM spending ORDER BY date DESC")
    suspend fun getAll(): List<SpendingEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spending: SpendingEntity)

    @Delete
    suspend fun delete(spending: SpendingEntity)
}