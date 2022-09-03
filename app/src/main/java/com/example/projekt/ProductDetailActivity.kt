package com.example.projekt

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class ProductDetailActivity : Activity(){
    var name: String? = null
    var quantity = 0
    var packaging:kotlin.String? = null
    var brands:kotlin.String? = null
    var categories:kotlin.String? = null
    var labels:kotlin.String? = null
    var stores:kotlin.String? = null
    var countriesWhereSold:kotlin.String? = null
    private var imageURL:kotlin.String? = null
    var url:kotlin.String? = null
    lateinit var nameTV: TextView
    lateinit  var quantityTV:TextView
    lateinit  var packagingTV:TextView
    lateinit var brandsTV: TextView
    lateinit var categoriesTV: TextView
    lateinit var labelsTV: TextView
    lateinit var storesTV: TextView
    lateinit var countriesWhereSoldTV: TextView
    lateinit var picIV: ImageView
    lateinit var readProductBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        name = intent.getStringExtra("name")
        quantity = intent.getIntExtra("quantity",0)
        packaging = intent.getStringExtra("packaging")
        brands = intent.getStringExtra("brands")
        categories = intent.getStringExtra("categories")
        labels = intent.getStringExtra("labels")
        stores = intent.getStringExtra("stores")
        countriesWhereSold = intent.getStringExtra("countriesWhereSold")
        imageURL = intent.getStringExtra("imageURL")
        url = intent.getStringExtra("url")
        nameTV = findViewById(R.id.idTVName)
        quantityTV = findViewById(R.id.idTVQuantity)
        packagingTV = findViewById(R.id.idTVPackaging)
        picIV = findViewById(R.id.idIVPic)
        brandsTV = findViewById(R.id.idTVBrands)
        categoriesTV = findViewById(R.id.idTVCategories)
        labelsTV = findViewById(R.id.idTVLabels)
        storesTV = findViewById(R.id.idTVStores)
        countriesWhereSoldTV = findViewById(R.id.idTVCountries)
        readProductBtn = findViewById(R.id.idBtnReadProduct)
        nameTV.setText(name)
        quantityTV.setText(quantity)
        packagingTV.setText(packaging)
        brandsTV.setText(brands)
        categoriesTV.setText(categories)
        labelsTV.setText(labels)
        storesTV.setText(stores)
        countriesWhereSoldTV.setText(countriesWhereSold)
        Picasso.get().load(imageURL).into(picIV)
        readProductBtn.setOnClickListener(View.OnClickListener {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        })
    }
}