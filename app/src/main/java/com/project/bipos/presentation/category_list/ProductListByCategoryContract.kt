package com.project.bipos.presentation.category_list

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView
import com.project.bipos.model.Product

interface ProductListByCategoryContract {

    interface View : BaseView {
        fun showProductListByCategory(productList: List<Product>?)
    }

    interface Presenter : BasePresenter {
        fun getProductListByCategory(category: String)
    }
}