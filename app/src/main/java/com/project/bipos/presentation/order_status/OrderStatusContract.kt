package com.project.bipos.presentation.order_status

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView
import com.project.bipos.model.Order

interface OrderStatusContract {

    interface View : BaseView {
        fun showOrderStatusList(orderList: List<Order>?)
    }

    interface Presenter : BasePresenter {
        fun getOrderStatusList(username: String)
    }
}