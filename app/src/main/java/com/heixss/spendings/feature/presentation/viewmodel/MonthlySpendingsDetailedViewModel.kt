package com.heixss.spendings.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.heixss.spendings.feature.domain.uimodel.MonthlySpendingsData
import com.heixss.spendings.feature.domain.usecases.MonthlySpendingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class MonthlySpendingsDetailedViewModel @Inject constructor(
    private val monthlySpendingsUseCase: MonthlySpendingsUseCase
) : ViewModel() {

    var month: Int = 0
    var year: Int = 0

    val spendings: Flow<MonthlySpendingsData> = flow {
        val result = monthlySpendingsUseCase(month, year)
        emit(result)
    }.flowOn(Dispatchers.IO)
}