package com.project.bipos.presentation.register

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView
import com.project.bipos.model.User

interface RegisterContract {

    interface View : BaseView{
        fun showUserRegisterSuccess()
    }

    interface Presenter : BasePresenter {
        fun postRegister(user: User)
    }
}