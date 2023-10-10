package com.heixss.spendings.feature.presentation.ui.screen

import com.heixss.spendings.feature.domain.model.TotalSpendingPerCategory

data class CategoriesScreenState(val totalSum: Double,
                                 val month:String,
                                 val totalSpendingPerCategoryList: List<TotalSpendingPerCategory>)
