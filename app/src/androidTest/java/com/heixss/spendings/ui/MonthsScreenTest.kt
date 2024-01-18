package com.heixss.spendings.ui

import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heixss.spendings.feature.data.database.SpendingEntity
import com.heixss.spendings.feature.domain.model.Spending
import com.heixss.spendings.feature.presentation.ui.screen.MonthsScreen

@RunWith(AndroidJUnit4::class)
class MonthsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            // Initialize your Composable with sample data
            val spendings = rememberUpdatedState(listOf(
                Spending(spendingId = 8, description = "", day = 2, month = 9, year = 2023, sum = 321.0),
                Spending(spendingId = 8, description = "", day = 2, month = 9, year = 2023, sum = 321.0),
                Spending(spendingId = 8, description = "", day = 2, month = 9, year = 2023, sum = 321.0),
            ))
            MonthsScreen(
                spendings = spendings,
                onAddClick = {},
                onItemClick = { _, _ -> }
            )
        }
    }

    @Test
    fun testMonthlySpendingsScreen() {
        // Verify that the "Add" button is displayed
        composeTestRule
            .onNodeWithText("Add")
            .assertExists()

        // Verify that each MonthlySpendingItem displays the correct text
        composeTestRule
            .onNodeWithText("9/2023")
            .assertIsDisplayed()

        // You can add more assertions as needed based on your UI logic
    }
}
