package com.heixss.spendings.feature.data.repositories

import com.heixss.spendings.feature.data.database.AppDatabase
import com.heixss.spendings.feature.data.database.CategoryEntity
import com.heixss.spendings.feature.data.database.SpendingEntity
import com.heixss.spendings.feature.domain.model.Category
import com.heixss.spendings.feature.domain.model.Spending
import com.heixss.spendings.feature.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val appDatabase: AppDatabase
) : MainRepository {

    override fun allSpendingsFlow(): Flow<List<Spending>> = appDatabase.spendingDao().allSpendings().map {
        return@map it.map { spendingEntity ->
            Spending(
                description = spendingEntity.description,
                checkImagePath = spendingEntity.checkImagePath,
                sum = spendingEntity.value,
                day = spendingEntity.day,
                month = spendingEntity.month,
                year = spendingEntity.year,
                spendingId = spendingEntity.id
            )
        }
    }

    override fun spendingsFlow(month: Int, year: Int): Flow<List<Spending>> =
        appDatabase.spendingDao().allSpendings(month, year).map {
            return@map it.map { spendingEntity ->
                Spending(
                    description = spendingEntity.description,
                    checkImagePath = spendingEntity.checkImagePath,
                    sum = spendingEntity.value,
                    day = spendingEntity.day,
                    month = spendingEntity.month,
                    year = spendingEntity.year,
                    spendingId = spendingEntity.id
                )
            }
        }

    override fun allCategoriesFlow(): Flow<List<Category>> {
        return appDatabase.categoryDao().getAllCategories().map {
            return@map it.map { categoryEntity ->
                Category(categoryEntity.id, categoryEntity.name)
            }
        }
    }

    override suspend fun insertCategory(name: String) {
        appDatabase.categoryDao().insert(CategoryEntity(name = name))
    }

    override suspend fun insertSpending(categoryId: Long, description: String, checkImagePath: String?, value: Double) {
        val cal: Calendar = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        appDatabase.spendingDao().insert(
            SpendingEntity(
                categoryId = categoryId,
                description = description,
                checkImagePath = checkImagePath,
                value = value,
                day = cal.get(Calendar.DAY_OF_MONTH),
                month = cal.get(Calendar.MONTH) + 1, //cuz 0 is january
                year = cal.get(Calendar.YEAR)
            )
        )
    }

    override suspend fun getCategoryByName(category: String): Category? {
        appDatabase.categoryDao().getCategory(category)?.let { categoryEntity ->
            return Category(categoryEntity.id, categoryEntity.name)
        } ?: run { return null }
    }

    override suspend fun getCategoryById(categoryId: Long): Category {
        return appDatabase.categoryDao().getCategoryById(categoryId).let { categoryEntity ->
            return@let Category(categoryId = categoryEntity.id, name = categoryEntity.name)
        }
    }

    override suspend fun removeSpending(spendingId: Long) {
        appDatabase.spendingDao().deleteSpending(spendingId)
    }

    override suspend fun clearSpendings() {
        appDatabase.spendingDao().clearAll()
    }

    override fun getSpendings(month: Int, year: Int): Flow<List<Spending>> =
        appDatabase.spendingDao().getSpendings(month, year).map {
            return@map it.map { spendingEntity ->
                Spending(
                    description = spendingEntity.description,
                    checkImagePath = spendingEntity.checkImagePath,
                    sum = spendingEntity.value,
                    day = spendingEntity.day,
                    month = spendingEntity.month,
                    year = spendingEntity.year,
                    spendingId = spendingEntity.id
                )
            }
        }

    override suspend fun getSpendingsByCategoryId(
        categoryId: Long,
        month: Int,
        year: Int
    ): List<Spending> {
        return appDatabase.spendingDao().getSpendingsByCategoryId(categoryId, month, year).map { spendingEntity ->
            Spending(
                description = spendingEntity.description,
                checkImagePath = spendingEntity.checkImagePath,
                sum = spendingEntity.value,
                day = spendingEntity.day,
                month = spendingEntity.month,
                year = spendingEntity.year,
                spendingId = spendingEntity.id
            )
        }
    }
}

