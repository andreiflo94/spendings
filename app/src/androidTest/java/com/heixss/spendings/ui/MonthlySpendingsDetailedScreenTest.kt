package com.heixss.spendings.ui

import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heixss.spendings.feature.data.database.CategoryEntity
import com.heixss.spendings.feature.domain.uimodel.TotalSpendingPerCategory
import com.heixss.spendings.feature.domain.uimodel.MonthlySpendingsData
import com.heixss.spendings.feature.presentation.ui.screen.MonthlySpendingsDetailedScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MonthlySpendingsDetailedScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testMonthlySpendingsDetailedScreen() {
        composeTestRule.setContent {
            val uiModel = rememberUpdatedState(MonthlySpendingsData(
                totalSum = 1000.0,
                totalSpendingPerCategoryList = listOf(
                    TotalSpendingPerCategory(CategoryEntity(1, "Category 1"), 300.0),
                    TotalSpendingPerCategory(CategoryEntity(2, "Category 2"), 500.0),
                    TotalSpendingPerCategory(CategoryEntity(3, "Category 3"), 200.0)
                )
            ))
            MonthlySpendingsDetailedScreen(
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