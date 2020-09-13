package com.project.bipos.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.bipos.R
import com.project.bipos.model.Product
import kotlinx.android.synthetic.main.item_product_list.view.*

class ProductSearchResultAdapter(private val onItemClicked: (product: Product) -> Unit) :
    RecyclerView.Adapter<ProductSearchResultAdapter.ProductViewHolder>() {

    private val productList = mutableListOf<Product>()

    fun addItems(products: List<Product>) {
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_product_list, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holderHomeMenu: ProductViewHolder, position: Int) {
        val product = productList[position]
        holderHomeMenu.bind(product)
        holderHomeMenu.itemView.setOnClickListener { onItemClicked(product) }
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            Glide.with(itemView)
                .load(product.image)
                .into(itemView.img_product)
            itemView.tv_productName.text = product.name
            itemView.tv_productBrand.text = product.brand
            itemView.tv_productPrice.text = "Rp. ${product.price}"
        }

    }

}