package com.project.bipos.presentation.category_list

import com.google.firebase.database.*
import com.project.bipos.model.Product

class ProductListByCategoryPresenter(private val view: ProductListByCategoryContract.View) :
    ProductListByCategoryContract.Presenter {

    private var productsList: MutableList<Product> = mutableListOf()

    override fun start() {
        view.initView()
    }

    override fun getProductListByCategory(category: String) {
        view.showLoading()
        val reference = FirebaseDatabase.getInstance().reference
        val query = reference.child("products").orderByChild("type").equalTo(category)
        query.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                view.hideLoading()
                for (postSnapshot in snapshot.children) {
                    val product = postSnapshot.getValue(Product::class.java)
                    productsList.add(product!!)
                }

                view.showProductListByCategory(productsList)
            }

        })
    }
}