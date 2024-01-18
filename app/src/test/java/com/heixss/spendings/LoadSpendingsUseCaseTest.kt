package com.heixss.spendings

import androidx.compose.runtime.mutableStateOf
import com.heixss.spendings.feature.domain.model.Category
import com.heixss.spendings.feature.domain.model.Spending
import com.heixss.spendings.feature.domain.repository.MainRepository
import com.heixss.spendings.feature.domain.usecases.LoadSpendingsUseCase
import com.heixss.spendings.feature.presentation.ui.screen.SpendingsScreenState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LoadSpendingsUseCaseTest {

    private lateinit var loadSpendingsUseCase: LoadSpendingsUseCase
    private val mockMainRepository: MainRepository = mock()

    @Before
    fun setup() {
        loadSpendingsUseCase = LoadSpendingsUseCase(mockMainRepository)
    }

    @Test
    fun `load spendings should return SpendingsScreenState`() = runBlocking {
        // Arrange
        val categoryId = 1L
        val month = 10
        val year = 2023
        val mockedSpending =  Spending(
            "desc",
            210.0,
            17,
            1,
            2024,
            3
        )
        val mockedCategory =  Category(categoryId = 3, name = "Food")

        val expectedSpendingList = listOf(
            mockedSpending
        )
        val expectedSpendingsScreenState = SpendingsScreenState("Food", mutableStateOf(expectedSpendingList))
        whenever(mockMainRepository.getCategoryById(3)).thenReturn(
            Category(categoryId = 3, name = "Food")
        )
        whenever(mockMainRepository.getSpendingsByCategoryId(categoryId, month, year)).thenReturn(
            listOf(
                mockedSpending
            )
        )
        whenever(mockMainRepository.getCategoryById(categoryId)).thenReturn(
            mockedCategory
        )


        // Act
        val result = loadSpendingsUseCase(categoryId, month, year)

        // Extract the values from the MutableState instances
        val expectedSpendingsList = expectedSpendingsScreenState.spendings.value
        val actualSpendingsList = result.spendings.value

        // Assert the content of the SpendingsScreenState
        assertEquals(expectedSpendingsScreenState.category, result.category)
        assertEquals(expectedSpendingsList, actualSpendingsList)
    }

}
