package com.heixss.spendings.feature.domain.usecases

import androidx.compose.runtime.mutableStateOf
import com.heixss.spendings.feature.data.repositories.MainRepository
import com.heixss.spendings.feature.domain.model.SpendingModel
import com.heixss.spendings.feature.presentation.ui.screen.SpendingsScreenState
import javax.inject.Inject

class LoadSpendingsUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend operator fun invoke(categoryId: Long, month: Int, year: Int): SpendingsScreenState {
        val list = ArrayList<SpendingModel>()
        val spendings = mainRepository.getSpendingsByCategoryId(categoryId, month, year)
        var categoryName = ""
        spendings.forEach { spending ->
            mainRepository.getCategoryById(spending.categoryId).let { category ->
                if(categoryName.isEmpty()) categoryName = category.name
                list.add(
                    SpendingModel(
                        description = spending.description,
                        date = String.format("%d/%d/%d", spending.day, spending.month, spending.year),
                        sum = spending.value,
                        spendingId = spending.id
                    )
                )
            }
        }
        return SpendingsScreenState(categoryName, mutableStateOf(list))
    }
}

