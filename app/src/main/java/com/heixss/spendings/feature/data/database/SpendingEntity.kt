package com.heixss.spendings.feature.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SpendingEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val categoryId: Long,
    val description: String = "",
    var value: Double,
    var day: Int,
    var month: Int,
    var year: Int
)