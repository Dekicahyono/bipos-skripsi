package com.project.bipos.presentation.detail.product

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView
import com.project.bipos.model.Order
import com.project.bipos.model.Product

interface ProductDetailsContract {

    interface View : BaseView {
        fun showProductsDetails(product: Product)
        fun showAddToCartSuccess(msg: String)
    }

    interface Presenter : BasePresenter {
        fun getProductDetails(product: Product)
        fun postProductToCart(order: Order)
    }
}