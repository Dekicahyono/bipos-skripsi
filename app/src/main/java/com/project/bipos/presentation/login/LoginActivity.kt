package com.project.bipos.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.project.bipos.presentation.main.MainActivity
import com.project.bipos.R
import com.project.bipos.base.BaseActivity
import com.project.bipos.db.SharedPreference
import com.project.bipos.presentation.register.RegisterActivity
import com.project.bipos.utils.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    private lateinit var presenter: LoginPresenter

    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreference = SharedPreference(this)
        presenter = LoginPresenter(this)
        presenter.start()

    }

    override fun initView() {
        initProgressBar(pb_login)
        parent_layout.setOnClickListener { hideKeyboard() }

        btn_login.setOnClickListener {
            if (edt_username.text.isNotEmpty() && edt_password.text.isNotEmpty()) {
                val username = edt_username.text.toString()
                val password = edt_password.text.toString()

                presenter.loginUser(username, password)
            }
        }

        tv_registerHere.setOnClickListener { startRegisterActivity() }
    }

    override fun showLoginSuccess(dataSnapshot: DataSnapshot) {
        val username = dataSnapshot.child("username").value.toString()
        val phoneNumber = dataSnapshot.child("phoneNumber").value.toString()

        showToast("Hallo, $username")

        sharedPreference.saveBoolean(KEY_IS_LOGIN, true)
        sharedPreference.saveString(KEY_USERNAME, username)
        sharedPreference.saveString(KEY_PHONE_NUMBER, phoneNumber)

        startMainActivity()
    }

    private fun startMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun startRegisterActivity() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }
}