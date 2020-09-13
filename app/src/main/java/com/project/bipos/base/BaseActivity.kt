package com.project.bipos.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.project.bipos.utils.hide
import com.project.bipos.utils.show
import com.project.bipos.utils.showToast

open class BaseActivity : AppCompatActivity(), BaseView {

    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    fun initProgressBar(progressBar: ProgressBar) {
        this.progressBar = progressBar
    }

    override fun initView() {
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun showMessage(msg: String) {
        showToast(msg)
    }

}