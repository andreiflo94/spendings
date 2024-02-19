package com.heixss.spendings.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heixss.spendings.feature.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddSpendingViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun addSpending(category: String, description: String, checkImagePath: String?, amount: Double, timeStamp: Long) {
        viewModelScope.launch {
            val cal: Calendar = Calendar.getInstance()
            cal.timeInMillis = timeStamp

            mainRepository.getCategoryByName(category)?.let { category ->
                category.let {
                    mainRepository.insertSpending(
                        categoryId = category.categoryId,
                        description = description,
                        checkImagePath = checkImagePath,
                        value = amount
                    )
                }
            } ?: run {
                mainRepository.insertCategory(name = category)
                addSpending(category, description, checkImagePath, amount, timeStamp)
            }

        }

    }
}
