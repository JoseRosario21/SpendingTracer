package com.jrosario.d04052022.spendingtracer.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.domain.model.SpendingCategory

@Entity(tableName = "spending")
data class SpendingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val amount: Double,
    val category: Int,
    val date: String
)

fun SpendingEntity.toDomain(): Spending = Spending(
    id, name, amount, SpendingCategory.entries[category], date
)