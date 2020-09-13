package com.project.bipos.presentation.detail.product

import android.os.Handler
import com.google.firebase.database.*
import com.project.bipos.model.Order
import com.project.bipos.model.Product
import com.project.bipos.presentation.detail.product.ProductDetailsContract

class ProductDetailsPresenter(private val view: ProductDetailsContract.View) : ProductDetailsContract.Presenter {

    var reference: DatabaseReference? = null

    override fun start() {
        view.initView()
    }

    override fun getProductDetails(product: Product) {
        view.showLoading()
        Handler().postDelayed({
            view.hideLoading()
            view.showProductsDetails(product)
        }, 2000)
    }

    override fun postProductToCart(order: Order) {
        view.showLoading()
        reference = FirebaseDatabase.getInstance().reference.child("users").child(order.customerName).child("orders").child(order.productId!!)
        reference!!.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                view.hideLoading()
                dataSnapshot.ref.setValue(order)
                view.showAddToCartSuccess("Berhasil ditambahkan ke keranjang")
            }

        })
    }

}