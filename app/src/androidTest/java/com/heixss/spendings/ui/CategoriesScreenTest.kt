package com.heixss.spendings.ui

import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heixss.spendings.feature.data.database.CategoryEntity
import com.heixss.spendings.feature.domain.model.TotalSpendingPerCategory
import com.heixss.spendings.feature.presentation.ui.screen.CategoriesScreenState
import com.heixss.spendings.feature.presentation.ui.screen.CategoriesScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoriesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMonthlySpendingsDetailedScreen() {
        composeTestRule.setContent {
            val uiModel = rememberUpdatedState(
                CategoriesScreenState(
                totalSum = 1000.0,
                month = "21/12",
                totalSpendingPerCategoryList = listOf(
                    TotalSpendingPerCategory(categoryId = 1, categoryName = "Category 1", totalSpendingValue = 600.0),
                    TotalSpendingPerCategory(categoryId = 2, categoryName = "Category 2", totalSpendingValue = 700.0),
                    TotalSpendingPerCategory(categoryId = 3, categoryName = "Category 3", totalSpendingValue = 200.0)
                )
            )
            )
            CategoriesScreen(
                uiModel,
                onItemClick = {}
            )
        }

        // Assertions
        composeTestRule.onNodeWithText("Category 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Category 2").assertIsDisplayed()
        composeTestRule.onNodeWithText("Category 3").assertIsDisplayed()
        // Click on an item
        composeTestRule.onNodeWithText("Category 1").performClick()

    }
}