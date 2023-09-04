package com.heixss.spendings.feature.domain.usecases

import com.heixss.spendings.feature.data.repositories.MainRepository
import com.heixss.spendings.feature.domain.uimodel.TotalSpendingPerCategory
import com.heixss.spendings.feature.domain.uimodel.MonthlySpendingsData
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class MonthlySpendingsUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend operator fun invoke(month: Int, year: Int): MonthlySpendingsData {
        val categories = mainRepository.allCategoriesFlow().first()

        val list = ArrayList<TotalSpendingPerCategory>()
        var totalCategoriesSum = 0.0

        for (category in categories) {
            val spendings = mainRepository.getSpendingsByCategoryId(category.id, month, year)
            val totalSum = spendings.sumOf { it.value }
            totalCategoriesSum += totalSum

            if (totalSum != 0.0) {
                list.add(TotalSpendingPerCategory(category, totalSum))
            }
        }

        return MonthlySpendingsData(totalCategoriesSum, list)
    }
}
