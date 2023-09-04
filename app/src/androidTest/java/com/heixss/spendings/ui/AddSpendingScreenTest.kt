package com.heixss.spendings.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.heixss.spendings.feature.presentation.ui.screen.AddSpendingScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddSpendingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAddSpendingScreen() {
        var capturedCategory = ""
        var capturedDescription = ""
        var capturedValue = ""

        composeTestRule.setContent {
            AddSpendingScreen { category, description, value ->
                capturedCategory = category
                capturedDescription = description
                capturedValue = value
            }
        }

        // Type into the Category TextField
        composeTestRule.onNodeWithTag("CategoryTextField")
            .performTextInput("Groceries")

        // Type into the Description TextField
        composeTestRule.onNodeWithTag("DescriptionTextField")
            .performTextInput("Weekly shopping")

        // Type into the Value TextField
        composeTestRule.onNodeWithTag("ValueTextField")
            .performTextInput("100.00")

        // Click on the Add button
        composeTestRule.onNodeWithText("Add")
            .performClick()

        // Assertions
        assert(capturedCategory == "Groceries")
        assert(capturedDescription == "Weekly shopping")
        assert(capturedValue == "100.00")
    }
}