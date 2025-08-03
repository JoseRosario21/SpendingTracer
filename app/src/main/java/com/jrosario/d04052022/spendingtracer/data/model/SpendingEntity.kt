package com.jrosario.d04052022.spendingtracer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpendingEntity(
    @PrimaryKey(autoGenerate = true) var expenseId: Int = 0,
    @ColumnInfo(name = "expense_name") val name: String,
    @ColumnInfo(name = "expense_amount") val amount: Double,
    @ColumnInfo(name = "expense_category") val category: Int,
    @ColumnInfo(name = "expense_payment_method") val paymentMethod: Int,
    @ColumnInfo(name = "expense_date") val date: String
)
