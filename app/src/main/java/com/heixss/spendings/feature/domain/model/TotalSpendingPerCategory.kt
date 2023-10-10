package com.heixss.spendings.feature.domain.model

data class TotalSpendingPerCategory(
    val categoryName: String,
    val categoryId: Long,
    val totalSpendingValue: Double
)