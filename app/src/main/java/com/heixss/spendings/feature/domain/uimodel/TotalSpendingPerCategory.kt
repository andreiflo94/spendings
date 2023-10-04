package com.heixss.spendings.feature.domain.uimodel

import com.heixss.spendings.feature.data.database.CategoryEntity

data class TotalSpendingPerCategory(
    val categoryEntity: CategoryEntity,
    val totalSpending: Double
)