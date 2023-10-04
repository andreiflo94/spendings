package com.heixss.spendings.feature.domain.repository

import com.heixss.spendings.feature.data.database.AppDatabase
import com.heixss.spendings.feature.data.database.CategoryEntity
import com.heixss.spendings.feature.data.database.SpendingEntity
import com.heixss.spendings.feature.data.repositories.MainRepository

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : MainRepository {

    override fun allSpendingsFlow(): Flow<List<SpendingEntity>> = appDatabase.spendingDao().allSpendings()

    override fun spendingsFlow(month: Int, year: Int): Flow<List<SpendingEntity>> =
        appDatabase.spendingDao().allSpendings(month, year)

    override fun allCategoriesFlow(): Flow<List<CategoryEntity>> {
        return appDatabase.categoryDao().getAllCategories()
    }

    override suspend fun insertCategory(categoryEntity: CategoryEntity) {
        appDatabase.categoryDao().insert(categoryEntity)
    }

    override suspend fun insertSpending(spendingEntity: SpendingEntity) {
        appDatabase.spendingDao().insert(spendingEntity)
    }

    override suspend fun getCategoryByName(category: String): CategoryEntity? {
        return appDatabase.categoryDao().getCategory(category)
    }

    override suspend fun getCategoryById(categoryId: Long): CategoryEntity {
        return appDatabase.categoryDao().getCategoryById(categoryId)
    }

    override suspend fun removeSpending(spendingId: Long) {
        appDatabase.spendingDao().deleteSpending(spendingId)
    }

    override suspend fun clearSpendings() {
        appDatabase.spendingDao().clearAll()
    }

    override fun getSpendings(month: Int, year: Int): Flow<List<SpendingEntity>> =
        appDatabase.spendingDao().getSpendings(month, year)

    override suspend fun getSpendingsByCategoryId(
        categoryId: Long,
        month: Int,
        year: Int
    ): List<SpendingEntity> {
        return appDatabase.spendingDao().getSpendingsByCategoryId(categoryId, month, year)
    }
}

