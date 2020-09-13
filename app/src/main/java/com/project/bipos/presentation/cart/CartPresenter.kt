package com.project.bipos.presentation.cart

import com.google.firebase.database.*
import com.project.bipos.model.Order

class CartPresenter(private val view: CartContract.View) : CartContract.Presenter {

    private var reference: DatabaseReference? = null
    private var orderList: MutableList<Order> = mutableListOf()

    override fun start() {
        view.initView()
    }

    override fun getOrderList(username: String) {
        view.showLoading()
        reference = FirebaseDatabase.getInstance().getReference("users").child(username).child("orders")
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                view.hideLoading()
                for (ordersSnapshot in snapshot.children) {
                    val order = ordersSnapshot.getValue(Order::class.java)
                    orderList.add(order!!)
                }

                if (orderList.size > 0) {
                    view.showOrderList(orderList)
                    view.hideEmptyListState()
                } else {
                    view.showEmptyListState()
                }
            }

        })
    }

}