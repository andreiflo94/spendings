package com.heixss.spendings

import com.heixss.spendings.feature.data.repositories.MainRepository
import com.heixss.spendings.feature.domain.usecases.RemoveSpendingUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoveSpendingUseCaseTest {

    private lateinit var removeSpendingUseCase: RemoveSpendingUseCase
    private val mockMainRepository: MainRepository = mock()

    @Before
    fun setup() {
        removeSpendingUseCase = RemoveSpendingUseCase(mockMainRepository)
    }

    @Test
    fun `remove spending should delete the spending item`() = runBlocking {
        // Arrange
        val spendingId = 1L
        // Mock the repository's removeSpending method
        whenever(mockMainRepository.removeSpending(spendingId)).thenReturn(Unit)

        // Act
        removeSpendingUseCase(spendingId)

        // Assert
        // Use Mockito verify to ensure that the removeSpending method was called with the correct argument
        verify(mockMainRepository).removeSpending(spendingId)
    }
}