package com.project.bipos.presentation.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.bipos.R
import kotlinx.android.synthetic.main.item_categories.view.*

class CategoryAdapter(private val onItemClicked: (category: String) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = mutableListOf<String>()

    fun addItems(categories: List<String>) {
        categoryList.clear()
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_categories, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holderHomeMenu: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holderHomeMenu.bind(category)
        holderHomeMenu.itemView.setOnClickListener { onItemClicked(category) }
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: String) {
           itemView.tv_categoryName.text = category
        }

    }

}