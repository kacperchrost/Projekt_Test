package com.example.projekt

class CategoryRVModal(s: String, s1: String) {
    lateinit var category: String
    lateinit var categoryImageUrl: String

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CategoryRVModal

        if (category != other.category) return false
        if (categoryImageUrl != other.categoryImageUrl) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category?.hashCode() ?: 0
        result = 31 * result + (categoryImageUrl?.hashCode() ?: 0)
        return result
    }

    fun getCategory(): Any {
        return category!!
    }

    fun getCategoryImageUrl(): Any {
        return categoryImageUrl.toString()
    }
}