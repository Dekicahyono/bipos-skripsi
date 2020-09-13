package com.project.bipos.base

import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.project.bipos.utils.hide
import com.project.bipos.utils.show

open class BaseFragment : Fragment(), BaseView {

    lateinit var progressBar: ProgressBar

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
        progressBar.hide()
    }

}