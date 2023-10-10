package com.heixss.spendings

import androidx.compose.runtime.mutableStateOf
import com.heixss.spendings.feature.data.database.CategoryEntity
import com.heixss.spendings.feature.data.database.SpendingEntity
import com.heixss.spendings.feature.data.repositories.MainRepository
import com.heixss.spendings.feature.domain.model.SpendingModel
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
        val expectedSpendingModelList = listOf(
            SpendingModel(
                "desc",
                210.0,
                "10/11/2013",
                3
            )
        )
        val expectedSpendingsScreenState = SpendingsScreenState("Food", mutableStateOf(expectedSpendingModelList))
        whenever(mockMainRepository.getCategoryById(3)).thenReturn(
            CategoryEntity(id = 3, name = "Food")
        )
        whenever(mockMainRepository.getSpendingsByCategoryId(categoryId, month, year)).thenReturn(
            listOf(
                SpendingEntity(
                    id = 3,
                    categoryId = 3,
                    description = "desc",
                    value = 210.0,
                    day = 10,
                    month = 11,
                    year = 2013
                )
            )
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
