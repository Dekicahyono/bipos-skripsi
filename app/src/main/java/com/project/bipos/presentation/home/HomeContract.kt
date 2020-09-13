package com.project.bipos.presentation.home

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView
import com.project.bipos.model.Banner
import com.project.bipos.model.Category
import com.project.bipos.model.Product

interface HomeContract {

    interface View: BaseView {
        fun showBannerList(bannerList: List<Banner>?)
        fun showCategoryList(categoryList: List<Category>?)
        fun showProductPopularList(productList: List<Product>?)
        fun showBestPriceProductList(bestPriceProductList: List<Product>?)
        fun showResultSearch(productList: List<Product>?)
    }

    interface Presenter: BasePresenter {
        fun getBannerList()
        fun getCategories()
        fun getProductPopularList()
        fun getBestPriceProductList()
        fun getProductBySearch(search: String)
    }
}