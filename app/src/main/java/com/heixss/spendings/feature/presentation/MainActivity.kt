package com.heixss.spendings.feature.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.heixss.spendings.feature.presentation.ui.screen.CategoriesScreenState
import com.heixss.spendings.feature.presentation.ui.screen.AddSpendingScreen
import com.heixss.spendings.feature.presentation.ui.screen.CategoriesScreen
import com.heixss.spendings.feature.presentation.ui.screen.MonthsScreen
import com.heixss.spendings.feature.presentation.ui.screen.SpendingsScreen
import com.heixss.spendings.feature.presentation.ui.theme.SpendingsTheme
import com.heixss.spendings.feature.presentation.viewmodel.AddSpendingViewModel
import com.heixss.spendings.feature.presentation.viewmodel.SpendingsViewModel
import com.heixss.spendings.feature.presentation.viewmodel.CategoriesScreenViewModel
import com.heixss.spendings.feature.presentation.viewmodel.MonthsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Create a NavController
        setContent {
            SpendingsTheme {
                SpendingsApp()
            }
        }
    }

    sealed class Screen(val route: String) {
        object AddSpendingScreen : Screen("add_spending")
        object MonthsScreen : Screen("months")
        object CategoriesScreen : Screen("categories")
        object SpendingsScreen : Screen("spendings")
    }

    @Composable
    fun SpendingsApp(
    ) {
        val navController = rememberNavController()
        NavHost(navController, startDestination = Screen.MonthsScreen.route) {
            composable(Screen.MonthsScreen.route) {
                val viewModel = hiltViewModel<MonthsViewModel>()
                MonthsScreen(
                    spendings = viewModel.allSpendingsFlow.collectAsStateWithLifecycle(initialValue = emptyList()),
                    onAddClick = {
                        navController.navigate(Screen.AddSpendingScreen.route)
                    },
                    onItemClick = { month, year ->
                        navController.navigate(Screen.CategoriesScreen.route + "?month=$month&year=$year")
                    }
                )
            }
            composable(
                route = Screen.CategoriesScreen.route + "?month={month}&year={year}",
                arguments = listOf(
                    navArgument(
                        name = "month"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                    navArgument(
                        name = "year"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                )
            ) { backStackEntry ->
                val navArgs = backStackEntry.arguments
                var month = 0
                navArgs?.getInt("month")?.let {
                    month = it
                }
                val year = navArgs?.getInt("year") ?: 0

                val viewModel = hiltViewModel<CategoriesScreenViewModel>()
                viewModel.month = month
                viewModel.year = year

                CategoriesScreen(uiModel = viewModel.spendings.collectAsStateWithLifecycle(CategoriesScreenState(0.0, "", emptyList())),
                    onItemClick = { categoryId ->
                        navController.navigate(
                            Screen.SpendingsScreen.route +
                                    "?categoryId=$categoryId&month=$month&year=$year"
                        )
                    })

            }
            composable(Screen.AddSpendingScreen.route) {
                val viewModel = hiltViewModel<AddSpendingViewModel>()
                val context = LocalContext.current
                AddSpendingScreen(
                    onAddClick = { category, description, value ->
                        if (category.isEmpty() || description.isEmpty() || value.isEmpty()) {
                            Toast.makeText(context, "Fields must not be empty", Toast.LENGTH_SHORT).show()
                            return@AddSpendingScreen
                        }

                        val timestamp: Long = System.currentTimeMillis()
                        viewModel.addSpending(
                            category.trim().toLowerCase(),
                            description.trim(),
                            value.toDouble(),
                            timestamp
                        )
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    }
                )
            }
            composable(
                route = Screen.SpendingsScreen.route + "?categoryId={categoryId}&month={month}&year={year}",
                arguments = listOf(
                    navArgument(
                        name = "categoryId"
                    ) {
                        type = NavType.LongType
                        defaultValue = -1
                    },
                    navArgument(
                        name = "month"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                    navArgument(
                        name = "year"
                    ) {
                        type = NavType.IntType
                        defaultValue = -1
                    },
                )
            ) { backStackEntry ->

                val navArgs = backStackEntry.arguments
                val categoryId = navArgs?.getLong("categoryId") ?: 0
                val month = navArgs?.getInt("month") ?: 0
                val year = navArgs?.getInt("year") ?: 0

                val viewModel = hiltViewModel<SpendingsViewModel>()

                viewModel.loadSpendings(categoryId, month, year)

                SpendingsScreen(
                    uiCategorySpendings = viewModel.state.collectAsStateWithLifecycle(),
                    onDelete = { spendingId ->
                        viewModel.removeSpending(spendingId)
                    }
                )
            }
        }
    }

}