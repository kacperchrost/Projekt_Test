package com.example.projekt

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : Activity() {

    //487d7842dcf84c57a9d658a1bc5bd9e6
    lateinit var foodRV: RecyclerView
    lateinit  var categoryRV:RecyclerView
    private var loadingPB: ProgressBar? = null
    private var productsArrayList: ArrayList<Food>? = null
    private var categoryRVModalArrayList: ArrayList<CategoryRVModal>? = null
    private var categoryRVAdapter: CategoryRVAdapter? = null
    private var productRVAdapter: ProductRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        foodRV = findViewById(R.id.idRVNews)
        categoryRV = findViewById(R.id.idRVCategories)
        loadingPB = findViewById(R.id.idPBLoading)
        productsArrayList = ArrayList()
        categoryRVModalArrayList = ArrayList()

        productRVAdapter = ProductRVAdapter(productsArrayList!!, this)
        categoryRVAdapter = CategoryRVAdapter(
            categoryRVModalArrayList!!,
            this
        ) { position: Int -> this.onCategoryClick(position) }
        foodRV.setLayoutManager(LinearLayoutManager(this))
        /*foodRV.adapter = productRVAdapter
        categoryRV.setAdapter(categoryRVAdapter)*/
        getCategories()
        getNews("All", getString(R.string.Country))
        //ProductRVAdapter.notifyDataSetChanged()
    }

    private fun getCategories() {
        categoryRVModalArrayList!!.add(
            CategoryRVModal(
                "USA",
                "https://images.unsplash.com/photo-1642890157128-a5c9be933a61?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80"
            )
        )
        categoryRVModalArrayList!!.add(
            CategoryRVModal(
                getString(R.string.Breakfasts),
                "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dGVjaG5vbG9neXxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"
            )
        )
        categoryRVModalArrayList!!.add(
            CategoryRVModal(
                getString(R.string.Spreads),
                "https://media.istockphoto.com/photos/vaccine-in-laboratory-flu-shot-and-covid19-vaccination-picture-id1289345741?b=1&k=20&m=1289345741&s=170667a&w=0&h=oG8iaDNP4rOLSgXWfeSziU3Vyu6KJS9Hn2ORohzSsRg="
            )
        )
        categoryRVModalArrayList!!.add(
            CategoryRVModal(
                getString(R.string.SweetSpreads),
                "https://media.istockphoto.com/photos/soccer-field-with-illumination-green-grass-and-cloudy-sky-background-picture-id1293105095?b=1&k=20&m=1293105095&s=170667a&w=0&h=1guu6B_WTHw5B4EShizGVRf3pyeBNNaGbtowrOLVjyM="
            )
        )
        categoryRVModalArrayList!!.add(
            CategoryRVModal(
                getString(R.string.ChocolateSpreads),
                "https://images.unsplash.com/photo-1432821596592-e2c18b78144f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"
            )
        )
        categoryRVModalArrayList!!.add(
            CategoryRVModal(
                getString(R.string.Waters),
                "https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8YnVzaW5lc3N8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60"
            )
        )
        categoryRVModalArrayList!!.add(
            CategoryRVModal(
                getString(R.string.Beverages),
                "https://media.istockphoto.com/photos/the-best-fans-a-band-could-want-picture-id502131959?b=1&k=20&m=502131959&s=170667a&w=0&h=8efBzeqguArASWGYfWhK5EdgNIJvZm-nvZ42i9DsS1A="
            )
        )
        categoryRVModalArrayList!!.add(
            CategoryRVModal(
                getString(R.string.Juice),
                "https://images.unsplash.com/photo-1571019613454-1cb2f99b2d8b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60"
            )
        )
    }

    private fun getNews(category: String, country: String) {
        loadingPB!!.visibility = View.VISIBLE
        productsArrayList?.clear()
        val categoryURL = "https://world.openfoodfacts.org/category/&category=$category"
        val url = "https://world.openfoodfacts.org/"
        val BASE_URL = "https://world.openfoodfacts.org/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call: Call<ProductModal?>?
        call = if (category == "All") {
            retrofitAPI.getAllNews(url)
        } else {
            retrofitAPI.getNewsByCategory(categoryURL)
        }
        call!!.enqueue(object : Callback<ProductModal?> {
            override fun onResponse(call: Call<ProductModal?>, response: Response<ProductModal?>) {
                val productModal: ProductModal? = response.body()
                loadingPB!!.visibility = View.GONE
                val products: ArrayList<Food>? = productModal?.getFood()
                if (products != null) {
                    for (i in products.indices) {
                        productsArrayList?.add(
                            Food(
                                products[i].name,
                                products[i].quantity,
                                products[i].urlToImage,
                                products[i].url,
                                products[i].packaging,
                                products[i].brands,
                                products[i].categories,
                                products[i].labels,
                                products[i].stores,
                                products[i].countriesWhereSold,
                            )
                        )
                    }
                }
                //productRVAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ProductModal?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Fail to get products", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun onCategoryClick(position: Int) {
        val category: String = categoryRVModalArrayList!![position].getCategory() as String
        getNews(category, getString(R.string.Country))
    }
}