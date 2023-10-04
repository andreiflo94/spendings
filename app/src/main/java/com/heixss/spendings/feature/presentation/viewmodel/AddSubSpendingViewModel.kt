package com.heixss.spendings.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heixss.spendings.feature.data.database.SpendingEntity
import com.heixss.spendings.feature.data.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddSubSpendingViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    var categoryId: Long = 0
    fun addSpending(categoryId: Long, description: String, amount: Double, timeStamp: Long) {
        viewModelScope.launch {
            val cal: Calendar = Calendar.getInstance()
            cal.timeInMillis = timeStamp
            mainRepository.insertSpending(
                SpendingEntity(
                    categoryId = categoryId,
                    description = description,
                    value = amount,
                    day = cal.get(Calendar.DAY_OF_MONTH),
                    month = cal.get(Calendar.MONTH),
                    year = cal.get(Calendar.YEAR)
                )
            )
        }
    }

}