package com.project.bipos.presentation.cart

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView
import com.project.bipos.model.Order

interface CartContract {

    interface View : BaseView {
        fun showOrderList(cartList: List<Order>?)
        fun showEmptyListState()
        fun hideEmptyListState()
    }

    interface Presenter : BasePresenter {
        fun getOrderList(username: String)
    }
}