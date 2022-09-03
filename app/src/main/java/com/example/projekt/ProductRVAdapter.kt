package com.example.projekt

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductRVAdapter(productsArrayList: ArrayList<Food>, mainActivity: MainActivity) {
    private val articlesArrayList: ArrayList<Food>? = null
    private val context: Context? = null

    fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.example.projekt.ProductRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_rv_item, parent, false)
        return com.example.projekt.ProductRVAdapter.ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV: TextView
        val foodIV: ImageView

        init {
            nameTV = itemView.findViewById(R.id.idTVProductHeading)
            foodIV = itemView.findViewById(R.id.idIVPic)
        }
    }

    fun getItemCount(): Int {
        return articlesArrayList!!.size
    }

    fun onBindViewHolder(
        holder: com.example.projekt.ProductRVAdapter.ViewHolder,
        position: Int
    ) {
        val products: Food = articlesArrayList!![position]
        holder.nameTV.setText(products.name)
        Picasso.get().load(products.urlToImage).into(holder.foodIV)
        holder.itemView.setOnClickListener(View.OnClickListener {
            val i = Intent(context, ProductDetailActivity::class.java)
            i.putExtra("name", products.name)
            i.putExtra("quantity", products.quantity)
            i.putExtra("packaging", products.packaging)
            i.putExtra("brands", products.brands)
            i.putExtra("categories", products.categories)
            i.putExtra("labels", products.labels)
            i.putExtra("stores", products.stores)
            i.putExtra("countriesWhereSold", products.countriesWhereSold)
            i.putExtra("image", products.urlToImage)
            i.putExtra("url", products.url)
            context!!.startActivity(i)
        })
    }
}