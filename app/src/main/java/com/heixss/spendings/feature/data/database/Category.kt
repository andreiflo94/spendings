package com.heixss.spendings.feature.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Category(@PrimaryKey(autoGenerate = true) val id: Long = 0, val name: String)