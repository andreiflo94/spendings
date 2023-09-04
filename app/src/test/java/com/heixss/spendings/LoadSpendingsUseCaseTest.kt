package com.heixss.spendings

import com.heixss.spendings.feature.data.database.Category
import com.heixss.spendings.feature.data.database.Spending
import com.heixss.spendings.feature.data.repositories.MainRepository
import com.heixss.spendings.feature.domain.uimodel.UISpendingModel
import com.heixss.spendings.feature.domain.usecases.LoadSpendingsUseCase
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
    fun `load spendings should return a list of UISpendingModel`() = runBlocking {
        // Arrange
        val categoryId = 1L
        val month = 9
        val year = 2023
        val expectedUISpendingList: List<UISpendingModel> = listOf(
            UISpendingModel(
                "Mancare",
                "desc",
                210.0,
                "9/11/2013",
                3
            )
        )
        whenever(mockMainRepository.getCategoryById(3)).thenReturn(
            Category(id = 3,
                name = "Mancare")
        )
        whenever(mockMainRepository.getSpendingsByCategoryId(categoryId, month, year)).thenReturn(
            listOf(
                Spending(
                    id = 3,
                    categoryId = 3,
                    description = "desc",
                    value = 210.0,
                    day = 9,
                    month = 11,
                    year = 2013
                )
            )
        )

        // Act
        val result = loadSpendingsUseCase(categoryId, month, year)

        // Assert
        assertEquals(expectedUISpendingList, result)
    }
}
