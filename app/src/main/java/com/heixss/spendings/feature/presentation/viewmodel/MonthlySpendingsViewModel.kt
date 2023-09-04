package com.heixss.spendings.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.heixss.spendings.feature.data.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MonthlySpendingsViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {
    val monthlySpendingsFlow = mainRepository.allSpendingsFlow()
}
