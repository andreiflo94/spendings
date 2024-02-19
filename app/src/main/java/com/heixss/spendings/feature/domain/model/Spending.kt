package com.heixss.spendings.feature.domain.model

data class Spending(
    val description: String,
    val sum: Double,
    val checkImagePath: String?,
    val day: Int,
    val month: Int,
    val year: Int,
    val spendingId: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Spending

        if (description != other.description) return false
        if (sum != other.sum) return false
        if (checkImagePath != other.checkImagePath) return false
        if (day != other.day) return false
        if (month != other.month) return false
        if (year != other.year) return false
        if (spendingId != other.spendingId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description.hashCode()
        result = 31 * result + sum.hashCode()
        result = 31 * result + checkImagePath.hashCode()
        result = 31 * result + day.hashCode()
        result = 31 * result + month.hashCode()
        result = 31 * result + year.hashCode()
        result = 31 * result + spendingId.hashCode()
        return result
    }
}
