package com.heixss.spendings.feature.data.repositories

import com.heixss.spendings.feature.data.database.Category
import com.heixss.spendings.feature.data.database.Spending
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun allSpendingsFlow(): Flow<List<Spending>>

    fun spendingsFlow(month: Int, year: Int): Flow<List<Spending>>

    fun allCategoriesFlow(): Flow<List<Category>>

    suspend fun insertCategory(category: Category)

    suspend fun insertSpending(spending: Spending)

    suspend fun getCategoryByName(category: String): Category?

    suspend fun getCategoryById(categoryId: Long): Category

    suspend fun removeSpending(spendingId: Long)

    suspend fun clearSpendings()

    fun getSpendings(month: Int, year: Int): Flow<List<Spending>>

    suspend fun getSpendingsByCategoryId(
        categoryId: Long,
        month: Int,
        year: Int
    ): List<Spending>
}