package com.project.bipos.presentation.delivery

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView
import com.project.bipos.model.Order

interface DeliveryContract {

    interface View : BaseView {
        fun showOrderSuccess()
    }

    interface Presenter : BasePresenter {
        fun deleteFromOrder(order: Order)
    }

}