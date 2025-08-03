package com.jrosario.d04052022.spendingtracer.domain.model

data class Spending(
    val id: Int,
    val category: String,
    val amount: Double,
    val date: Long
)
