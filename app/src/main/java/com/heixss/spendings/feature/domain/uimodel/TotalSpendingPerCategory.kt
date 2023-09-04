package com.heixss.spendings.feature.domain.uimodel

import com.heixss.spendings.feature.data.database.Category

data class TotalSpendingPerCategory(
    val category: Category,
    val totalSpending: Double
)