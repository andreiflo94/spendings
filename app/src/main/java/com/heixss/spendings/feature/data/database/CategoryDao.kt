package com.heixss.spendings.feature.data.database

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class CategoryDao : BaseDao<Category>() {

    @Query("SELECT * FROM category WHERE name = :category")
    abstract suspend fun getCategory(category: String): Category?

    @Query("SELECT * FROM category")
    abstract fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT * FROM category WHERE id = :categoryId")
    abstract suspend fun getCategoryById(categoryId: Long): Category
}
