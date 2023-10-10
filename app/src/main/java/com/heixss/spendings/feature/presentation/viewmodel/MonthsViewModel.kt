package com.heixss.spendings.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.heixss.spendings.feature.data.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MonthsViewModel @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {
    val allSpendingsFlow = mainRepository.allSpendingsFlow()
}
