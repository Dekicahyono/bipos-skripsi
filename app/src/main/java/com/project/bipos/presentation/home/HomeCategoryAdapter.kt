package com.project.bipos.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.bipos.R
import com.project.bipos.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class HomeCategoryAdapter(private val onItemClicked: (category: Category) -> Unit) :
    RecyclerView.Adapter<HomeCategoryAdapter.HomeCategoryViewHolder>() {

    private val categoryList = mutableListOf<Category>()

    fun addItems(categories: List<Category>) {
        categoryList.clear()
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category, parent, false)
        return HomeCategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holderHomeMenu: HomeCategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holderHomeMenu.bind(category)
        holderHomeMenu.itemView.setOnClickListener { onItemClicked(category) }
    }

    class HomeCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: Category) {
            itemView.img_icon.setImageResource(category.imgIcon)
            itemView.tv_name.text = category.name
        }

    }

}