package com.project.bipos.base

interface BaseView {

    fun initView()
    fun showLoading()
    fun hideLoading()
    fun showMessage(msg: String)

}