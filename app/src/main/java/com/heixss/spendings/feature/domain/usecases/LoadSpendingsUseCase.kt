package com.heixss.spendings.feature.domain.usecases

import com.heixss.spendings.feature.data.repositories.MainRepository
import com.heixss.spendings.feature.domain.uimodel.UISpendingModel
import javax.inject.Inject

class LoadSpendingsUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend operator fun invoke(categoryId: Long, month: Int, year: Int): List<UISpendingModel> {
        val list = ArrayList<UISpendingModel>()
        val spendings = mainRepository.getSpendingsByCategoryId(categoryId, month, year)
        spendings.forEach { spending ->
            mainRepository.getCategoryById(spending.categoryId).let { category ->
                list.add(
                    UISpendingModel(
                        category = category.name,
                        description = spending.description,
                        date = String.format("%d/%d/%d", spending.day, spending.month, spending.year),
                        sum = spending.value,
                        spendingId = spending.id
                    )
                )
            }
        }
        return list
    }
}

