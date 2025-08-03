package com.jrosario.d04052022.spendingtracer.domain.model

import com.jrosario.d04052022.spendingtracer.data.model.SpendingEntity

data class Spending(
    val id: Int = 0,
    val name: String,
    val amount: Double,
    val category: SpendingCategory,
    val date: String
)

fun Spending.toEntity(): SpendingEntity = SpendingEntity(
    id, name, amount, category.ordinal, date
)
