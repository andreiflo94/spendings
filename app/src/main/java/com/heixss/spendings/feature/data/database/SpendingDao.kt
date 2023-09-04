package com.heixss.spendings.feature.data.database

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Query
import com.heixss.spendings.feature.data.database.BaseDao
import com.heixss.spendings.feature.data.database.Spending

@Dao
abstract class SpendingDao : BaseDao<Spending>() {

    @Query("SELECT * FROM spending ORDER BY value DESC")
    abstract fun allSpendings(): Flow<List<Spending>>

    @Query("SELECT * FROM spending WHERE month = :month AND year = :year ORDER BY value DESC")
    abstract fun allSpendings(month: Int, year: Int): Flow<List<Spending>>

    @Query("SELECT * FROM spending WHERE month = :month AND year = :year ORDER BY value DESC")
    abstract fun getSpendings(month: Int, year: Int): Flow<List<Spending>>

    @Query("SELECT * FROM spending WHERE categoryId = :categoryId AND month = :month AND year =:year ORDER BY value DESC")
    abstract suspend fun getSpendingsByCategoryId(categoryId: Long, month: Int, year: Int): List<Spending>

    @Query("DELETE FROM spending WHERE id = :spendingId")
    abstract suspend fun deleteSpending(spendingId: Long)

    @Query("DELETE FROM spending")
    abstract suspend fun clearAll()
}
