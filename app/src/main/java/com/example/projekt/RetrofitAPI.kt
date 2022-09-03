package com.example.projekt

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url


interface RetrofitAPI {
    @GET
    fun getAllNews(@Url url: String?): Call<ProductModal?>?

    @GET
    fun getNewsByCategory(@Url url: String?): Call<ProductModal?>?
}