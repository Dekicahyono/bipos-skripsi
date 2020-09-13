package com.project.bipos.presentation.main

import android.util.Log
import com.google.firebase.database.*
import com.project.bipos.model.Order

class MainPresenter(private val view: MainContract.View): MainContract.Presenter {

    private var reference: DatabaseReference? = null
    private var orderList: MutableList<Order> = mutableListOf()

    override fun getCartCounter(username: String) {
        reference = FirebaseDatabase.getInstance().getReference("users").child(username).child("orders")
        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (ordersSnapshot in snapshot.children) {
                    val order = ordersSnapshot.getValue(Order::class.java)
                    orderList.add(order!!)
                }

                if (orderList.size > 0) {
                    view.showCartBadge()
                }
            }

        })
    }

    override fun checkUserLogin(isLogin: Boolean) {
        when (isLogin) {
            false -> view.hideCartBadge()
        }
    }
}