package com.example.projekt

class ProductModal {
    private var totalResults = 0
    private var status: String? = null
    private var food: ArrayList<Food>? = null

    fun getFood(): ArrayList<Food>? {
        return food
    }

}