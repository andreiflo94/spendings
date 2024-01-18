package com.heixss.spendings.feature.presentation.ui.screen

import androidx.compose.runtime.MutableState
import com.heixss.spendings.feature.domain.model.Spending

data class SpendingsScreenState(
    val category: String,
    var spendings : MutableState<List<Spending>>
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpendingsScreenState

        if (category != other.category) return false
        if (spendings != other.spendings) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category.hashCode()
        result = 31 * result + spendings.hashCode()
        return result
    }
}