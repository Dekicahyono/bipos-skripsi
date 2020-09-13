package com.project.bipos.presentation.category

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView

interface CategoryContract {

    interface View : BaseView {
        fun showCategoryList(categories: List<String>)
    }

    interface Presenter : BasePresenter {
        fun getCategoryList(categories: List<String>)
    }
}