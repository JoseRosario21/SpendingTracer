package com.jrosario.d04052022.spendingtracer.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AccountEntity(
    @PrimaryKey val accountId: Int = 0,
    @ColumnInfo(name = "bank_amount") var bankAmount: Double = 0.0,
    @ColumnInfo(name = "cash_amount") var cashAmount: Double = 0.0,
    @ColumnInfo(name = "food_allowance_amount") var foodAllowanceAmount: Double = 0.0,
    @ColumnInfo(name = "saving_amount") var savingAmount: Double = 0.0,
)
