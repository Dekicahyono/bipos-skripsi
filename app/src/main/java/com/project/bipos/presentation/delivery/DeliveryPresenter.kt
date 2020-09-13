package com.project.bipos.presentation.delivery

import com.google.firebase.database.*
import com.project.bipos.model.Order

class DeliveryPresenter(private val view: DeliveryContract.View) : DeliveryContract.Presenter {

    private var reference: DatabaseReference? = null

    override fun start() {
        view.initView()
    }

    override fun deleteFromOrder(order: Order) {
        view.showLoading()
        reference = FirebaseDatabase.getInstance().reference

        // for remove from cart
        val queryRemoveFromCart = reference!!.child("users").child(order.customerName).child("orders")
                .orderByChild("productId").equalTo(order.productId)
        queryRemoveFromCart.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (data in dataSnapshot.children) {
                    data.ref.removeValue()
                }
            }

        })

        // for add to order list
        val queryAddToOrder = reference!!.child("orders").child(order.customerName).child(order.orderNumber)
        queryAddToOrder.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.ref.setValue(order)
                view.showOrderSuccess()
                view.showMessage("Berhasil memesan")
            }

        })
    }

}