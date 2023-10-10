package com.heixss.spendings.feature.domain.model

data class SpendingModel(val description: String,
                         val sum: Double,
                         val date: String,
                         val spendingId: Long){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpendingModel

        if (description != other.description) return false
        if (sum != other.sum) return false
        if (date != other.date) return false
        if (spendingId != other.spendingId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = description.hashCode()
        result = 31 * result + sum.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + spendingId.hashCode()
        return result
    }
}
