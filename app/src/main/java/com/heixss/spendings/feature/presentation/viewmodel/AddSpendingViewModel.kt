package com.heixss.spendings.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heixss.spendings.feature.data.database.CategoryEntity
import com.heixss.spendings.feature.data.database.SpendingEntity
import com.heixss.spendings.feature.data.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddSpendingViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun addSpending(category: String, description: String, amount: Double, timeStamp: Long) {
        viewModelScope.launch {
            val cal: Calendar = Calendar.getInstance()
            cal.timeInMillis = timeStamp

            mainRepository.getCategoryByName(category)?.let { category ->
                category.let {
                    mainRepository.insertSpending(
                        SpendingEntity(
                            categoryId = category.id,
                            description = description,
                            value = amount,
                            day = cal.get(Calendar.DAY_OF_MONTH),
                            month = cal.get(Calendar.MONTH) + 1, //cuz 0 is january
                            year = cal.get(Calendar.YEAR)
                        )
                    )
                }
            } ?: run {
                mainRepository.insertCategory(CategoryEntity(name = category))
                addSpending(category, description, amount, timeStamp)
            }

        }

    }
}
