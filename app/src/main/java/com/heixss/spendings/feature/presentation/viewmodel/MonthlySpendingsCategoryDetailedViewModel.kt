package com.heixss.spendings.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heixss.spendings.feature.domain.uimodel.UISpendingModel
import com.heixss.spendings.feature.domain.usecases.LoadSpendingsUseCase
import com.heixss.spendings.feature.domain.usecases.RemoveSpendingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MonthlySpendingsCategoryDetailedViewModel @Inject constructor(
    private val loadSpendingsUseCase: LoadSpendingsUseCase,
    private val removeSpendingUseCase: RemoveSpendingUseCase
) : ViewModel() {
    var categoryId: Long = 0
    var month: Int = 0
    var year: Int = 0

    private val _state = MutableStateFlow<List<UISpendingModel>>(emptyList())
    val state: StateFlow<List<UISpendingModel>> = _state

    // Use the Load Spendings Use Case
    fun loadSpendings(categoryId: Long, month: Int, year: Int) {
        viewModelScope.launch {
            try {
                val spendings = loadSpendingsUseCase(categoryId, month, year)
                _state.value = spendings
            } catch (e: Exception) {
                // Handle any errors or exceptions here
            }
        }
    }

    // Use the Remove Spending Use Case
    fun removeSpending(spendingId: Long) {
        viewModelScope.launch {
            try {
                removeSpendingUseCase(spendingId)
                // Update the state after successful removal
                val newList = _state.value.filter { it.spendingId != spendingId }
                _state.value = newList
            } catch (e: Exception) {
                // Handle any errors or exceptions here
            }
        }
    }
}