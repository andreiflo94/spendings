package com.heixss.spendings.feature.data.database

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class CategoryDao : BaseDao<CategoryEntity>() {

    @Query("SELECT * FROM categoryentity WHERE name = :category")
    abstract suspend fun getCategory(category: String): CategoryEntity?

    @Query("SELECT * FROM categoryentity")
    abstract fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categoryentity WHERE id = :categoryId")
    abstract suspend fun getCategoryById(categoryId: Long): CategoryEntity
}
