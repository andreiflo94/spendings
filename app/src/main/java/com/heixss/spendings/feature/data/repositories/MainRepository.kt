package com.heixss.spendings.feature.data.repositories

import com.heixss.spendings.feature.data.database.CategoryEntity
import com.heixss.spendings.feature.data.database.SpendingEntity
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun allSpendingsFlow(): Flow<List<SpendingEntity>>

    fun spendingsFlow(month: Int, year: Int): Flow<List<SpendingEntity>>

    fun allCategoriesFlow(): Flow<List<CategoryEntity>>

    suspend fun insertCategory(categoryEntity: CategoryEntity)

    suspend fun insertSpending(spendingEntity: SpendingEntity)

    suspend fun getCategoryByName(category: String): CategoryEntity?

    suspend fun getCategoryById(categoryId: Long): CategoryEntity

    suspend fun removeSpending(spendingId: Long)

    suspend fun clearSpendings()

    fun getSpendings(month: Int, year: Int): Flow<List<SpendingEntity>>

    suspend fun getSpendingsByCategoryId(
        categoryId: Long,
        month: Int,
        year: Int
    ): List<SpendingEntity>
}