package com.project.bipos.presentation.login

import com.google.firebase.database.DataSnapshot
import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView

interface LoginContract {

    interface View: BaseView {
        fun showLoginSuccess(dataSnapshot: DataSnapshot)
    }

    interface Presenter: BasePresenter {
        fun loginUser(username: String, password: String)
    }
}