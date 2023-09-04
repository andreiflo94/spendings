package com.heixss.spendings.ui

import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heixss.spendings.feature.data.database.Spending
import com.heixss.spendings.feature.presentation.ui.screen.MonthlySpendingsScreen

@RunWith(AndroidJUnit4::class)
class MonthlySpendingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            // Initialize your Composable with sample data
            val spendings = rememberUpdatedState(listOf(
                Spending(8, 1, day = 2, month = 9, year = 2023, value = 321.0),
                Spending(8, 1, day = 2, month = 9, year = 2023, value = 321.0),
                Spending(8, 1, day = 2, month = 9, year = 2023, value = 321.0),
                // Add more sample data as needed
            ))
            MonthlySpendingsScreen(
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

        // Verify that the MonthlySpendingItem composable is displayed for each distinct month/year combination
        composeTestRule
            .onAllNodesWithTag("MonthlySpendingItem")

        // Verify that each MonthlySpendingItem displays the correct text
        composeTestRule
            .onNodeWithText("9/2023")
            .assertIsDisplayed()

        // You can add more assertions as needed based on your UI logic
    }
}
