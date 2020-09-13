package com.project.bipos.presentation.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.bipos.R
import com.project.bipos.base.BaseActivity
import com.project.bipos.model.User
import com.project.bipos.presentation.login.LoginActivity
import com.project.bipos.utils.hide
import com.project.bipos.utils.hideKeyboard
import com.project.bipos.utils.show
import com.project.bipos.utils.showToast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.View {

    private lateinit var presenter: RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter = RegisterPresenter(this)
        presenter.start()

    }

    override fun initView() {
        initProgressBar(pb_register)
        parent_layout.setOnClickListener { hideKeyboard() }

        btn_register.setOnClickListener {
            if (edt_username.text.isNotEmpty() && edt_fullName.text.isNotEmpty()
                && edt_phoneNumber.text.isNotEmpty() && edt_password.text.isNotEmpty()) {

                val user = User(
                    username = edt_username.text.toString(),
                    fullName = edt_fullName.text.toString(),
                    phoneNumber = edt_phoneNumber.text.toString(),
                    password = edt_password.text.toString()
                )
                presenter.postRegister(user)
            } else {
                showToast("Harap isi semua data !")
            }
        }

        tv_loginHere.setOnClickListener { startLoginActivity() }
    }

    override fun showUserRegisterSuccess() {
        showToast("Daftar Berhasil")
        startLoginActivity()
    }

    private fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}