package com.project.bipos.presentation.main

interface MainContract {

    interface View {
        fun showCartBadge()
        fun hideCartBadge()
    }

    interface Presenter {
        fun getCartCounter(username: String)
        fun checkUserLogin(isLogin: Boolean)
    }
}