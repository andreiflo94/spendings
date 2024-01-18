package com.heixss.spendings.feature.domain.usecases

import com.heixss.spendings.feature.domain.repository.MainRepository
import javax.inject.Inject

class RemoveSpendingUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend operator fun invoke(spendingId: Long) {
        mainRepository.removeSpending(spendingId = spendingId)
    }
}
