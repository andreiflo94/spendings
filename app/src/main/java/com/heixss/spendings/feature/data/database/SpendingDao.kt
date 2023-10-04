package com.heixss.spendings.feature.data.database

import kotlinx.coroutines.flow.Flow
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class SpendingDao : BaseDao<SpendingEntity>() {

    @Query("SELECT * FROM spendingentity ORDER BY value DESC")
    abstract fun allSpendings(): Flow<List<SpendingEntity>>

    @Query("SELECT * FROM spendingentity WHERE month = :month AND year = :year ORDER BY value DESC")
    abstract fun allSpendings(month: Int, year: Int): Flow<List<SpendingEntity>>

    @Query("SELECT * FROM spendingentity WHERE month = :month AND year = :year ORDER BY value DESC")
    abstract fun getSpendings(month: Int, year: Int): Flow<List<SpendingEntity>>

    @Query("SELECT * FROM spendingentity WHERE categoryId = :categoryId AND month = :month AND year =:year ORDER BY value DESC")
    abstract suspend fun getSpendingsByCategoryId(categoryId: Long, month: Int, year: Int): List<SpendingEntity>

    @Query("DELETE FROM spendingentity WHERE id = :spendingId")
    abstract suspend fun deleteSpending(spendingId: Long)

    @Query("DELETE FROM spendingentity")
    abstract suspend fun clearAll()
}
