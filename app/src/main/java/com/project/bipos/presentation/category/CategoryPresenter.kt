package com.project.bipos.presentation.category


class CategoryPresenter(private val view: CategoryContract.View) : CategoryContract.Presenter {

    override fun start() {
        view.initView()
    }

    override fun getCategoryList(categories: List<String>) {
        view.showCategoryList(categories)
    }

}