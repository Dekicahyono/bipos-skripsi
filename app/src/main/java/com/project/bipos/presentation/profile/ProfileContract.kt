package com.project.bipos.presentation.profile

import com.project.bipos.base.BasePresenter
import com.project.bipos.base.BaseView

interface ProfileContract {

    interface View: BaseView {
        fun showProfile()
        fun showLogoutSuccess(msg: String)
    }

    interface Presenter: BasePresenter {
        fun getProfile()
        fun logout()
    }
}