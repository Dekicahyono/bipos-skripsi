package com.project.bipos.presentation.order_status

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.bipos.model.Order

class OrderStatusPresenter(private val view: OrderStatusContract.View) : OrderStatusContract.Presenter {

    private val orderStatusList: MutableList<Order> = mutableListOf()

    override fun getOrderStatusList(username: String) {
        val reference = FirebaseDatabase.getInstance().getReference("users").child(username).child("orders_status")

        view.showLoading()
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                view.hideLoading()
                for (orderList in dataSnapshot.children) {
                    val order = orderList.getValue(Order::class.java)
                    orderStatusList.add(order!!)
                }

                view.showOrderStatusList(orderStatusList)
            }

        })
    }

    override fun start() {
        view.initView()
    }

}