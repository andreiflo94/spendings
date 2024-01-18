package com.heixss.spendings.feature.domain.usecases

import androidx.compose.runtime.mutableStateOf
import com.heixss.spendings.feature.domain.repository.MainRepository
import com.heixss.spendings.feature.domain.model.Spending
import com.heixss.spendings.feature.presentation.ui.screen.SpendingsScreenState
import javax.inject.Inject

class LoadSpendingsUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend operator fun invoke(categoryId: Long, month: Int, year: Int): SpendingsScreenState {
        val spendings = mainRepository.getSpendingsByCategoryId(categoryId, month, year)
        var categoryName = ""
        mainRepository.getCategoryById(categoryId).let { category ->
            if (categoryName.isEmpty()) categoryName = category.name
        }
        return SpendingsScreenState(categoryName, mutableStateOf(spendings))
    }
}

