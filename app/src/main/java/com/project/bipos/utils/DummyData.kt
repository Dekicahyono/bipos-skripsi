package com.project.bipos.utils

import com.project.bipos.R
import com.project.bipos.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

object DummyData {

    fun generateCategory(): List<Category> {
        val categories = mutableListOf<Category>()

        categories.add(Category(R.drawable.produklokal, "Produk Lokal"))
        categories.add(Category(R.drawable.produkimport, "Produk Import"))
        categories.add(Category(R.drawable.barangbekas, "Barang Bekas"))
        categories.add(Category(R.drawable.palinglaris, "Paling Laris"))
        categories.add(Category(R.drawable.banyakdiskon, "Banyak Diskon"))
        categories.add(Category(R.drawable.produkpopular, "Produk Popular"))
        categories.add(Category(R.drawable.bisanego, "Bisa Nego"))
        categories.add(Category(R.drawable.aksesoris, "Aksesoris"))

        return categories
    }

    fun generateCategories(): List<String> {
        val categories = mutableListOf<String>()

        categories.add("Filter")
        categories.add("Lampu")
        categories.add("Aksesoris")
        categories.add("Heater")
        categories.add("White Goods")
        categories.add("Sanitization")
        categories.add("Vacum")
        categories.add("Pompa")

        return categories
    }
}