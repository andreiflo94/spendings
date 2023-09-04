package com.heixss.spendings.feature.domain.uimodel

data class UISpendingModel(val category: String,
    val description: String,
    val sum: Double,
    val date: String,
    val spendingId: Long)
