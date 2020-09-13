package com.project.bipos.presentation.profile

import android.os.Handler

class ProfilePresenter(private val view: ProfileContract.View) : ProfileContract.Presenter {

    override fun start() {
        view.initView()
    }

    override fun getProfile() {
        view.showProfile()
    }

    override fun logout() {
        view.showLoading()
        Handler().postDelayed({
            view.hideLoading()
            view.showLogoutSuccess("Berhasil Keluar")
        }, 3000)
    }

}