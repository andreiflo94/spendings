package com.heixss.spendings.feature.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heixss.spendings.feature.domain.usecases.LoadSpendingsUseCase
import com.heixss.spendings.feature.domain.usecases.RemoveSpendingUseCase
import com.heixss.spendings.feature.presentation.ui.screen.SpendingsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SpendingsViewModel @Inject constructor(
    private val loadSpendingsUseCase: LoadSpendingsUseCase,
    private val removeSpendingUseCase: RemoveSpendingUseCase
) : ViewModel() {
    var categoryId: Long = 0
    var month: Int = 0
    var year: Int = 0

    private val _state = MutableStateFlow(SpendingsScreenState("",  mutableStateOf(emptyList())))
    val state: StateFlow<SpendingsScreenState> = _state

    // Use the Load Spendings Use Case
    fun loadSpendings(categoryId: Long, month: Int, year: Int) {
        viewModelScope.launch {
            try {
                val spendings = withContext(Dispatchers.IO){
                    loadSpendingsUseCase(categoryId, month, year)
                }
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
                val newList = withContext(Dispatchers.IO){
                    _state.value.spendings.value.filter { it.spendingId != spendingId }
                }

                _state.value.spendings.value = newList
            } catch (e: Exception) {
                // Handle any errors or exceptions here
            }
        }
    }
}