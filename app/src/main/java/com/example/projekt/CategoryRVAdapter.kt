package com.example.projekt

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class CategoryRVAdapter(
    categoryRVModalArrayList: ArrayList<CategoryRVModal>,
    mainActivity: MainActivity,
    function: (Int) -> Unit
) {
    private val categoryRVModals: ArrayList<CategoryRVModal>? = null
    private val context: Context? = null
    private val categoryClickInterface: com.example.projekt.CategoryRVAdapter.CategoryClickInterface? = null

    interface CategoryClickInterface {
        fun onCategoryClick(position: Int)
    }

    fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): com.example.projekt.CategoryRVAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.categories_rv_item, parent, false)
        return com.example.projekt.CategoryRVAdapter.ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTV: TextView
        val categoryIV: ImageView

        init {
            categoryTV = itemView.findViewById(R.id.idTVCategory)
            categoryIV = itemView.findViewById(R.id.idIVCategory)
        }
    }

    fun getItemCount(): Int {
        return categoryRVModals!!.size
    }

    fun onBindViewHolder(
        holder: com.example.projekt.CategoryRVAdapter.ViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val categoryRVModal = categoryRVModals!![position]
        holder.categoryTV.setText(categoryRVModal.category)
        Picasso.get().load(categoryRVModal.categoryImageUrl) to holder.categoryIV
        holder.itemView.setOnClickListener(View.OnClickListener {
            categoryClickInterface!!.onCategoryClick(
                position
            )
        })
    }
}


