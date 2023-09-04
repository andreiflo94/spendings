package com.heixss.spendings.feature.domain.repository

import com.heixss.spendings.feature.data.database.AppDatabase
import com.heixss.spendings.feature.data.database.Category
import com.heixss.spendings.feature.data.database.Spending
import com.heixss.spendings.feature.data.repositories.MainRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : MainRepository {

    override fun allSpendingsFlow(): Flow<List<Spending>> = appDatabase.spendingDao().allSpendings()

    override fun spendingsFlow(month: Int, year: Int): Flow<List<Spending>> =
        appDatabase.spendingDao().allSpendings(month, year)

    override fun allCategoriesFlow(): Flow<List<Category>> {
        return appDatabase.categoryDao().getAllCategories()
    }

    override suspend fun insertCategory(category: Category) {
        appDatabase.categoryDao().insert(category)
    }

    override suspend fun insertSpending(spending: Spending) {
        appDatabase.spendingDao().insert(spending)
    }

    override suspend fun getCategoryByName(category: String): Category? {
        return appDatabase.categoryDao().getCategory(category)
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return appDatabase.categoryDao().getCategoryById(categoryId)
    }

    override suspend fun removeSpending(spendingId: Long) {
        appDatabase.spendingDao().deleteSpending(spendingId)
    }

    override suspend fun clearSpendings() {
        appDatabase.spendingDao().clearAll()
    }

    override fun getSpendings(month: Int, year: Int): Flow<List<Spending>> =
        appDatabase.spendingDao().getSpendings(month, year)

    override suspend fun getSpendingsByCategoryId(
        categoryId: Long,
        month: Int,
        year: Int
    ): List<Spending> {
        return appDatabase.spendingDao().getSpendingsByCategoryId(categoryId, month, year)
    }
}

