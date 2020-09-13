package com.project.bipos.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.bipos.R
import com.project.bipos.base.BaseFragment
import com.project.bipos.db.SharedPreference
import com.project.bipos.presentation.login.LoginActivity
import com.project.bipos.presentation.login.LoginPresenter
import com.project.bipos.presentation.main.MainActivity
import com.project.bipos.presentation.order_status.OrderStatusActivity
import com.project.bipos.utils.*
import kotlinx.android.synthetic.main.fragment_cart.btn_login
import kotlinx.android.synthetic.main.fragment_cart.layout_login
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar.*


class ProfileFragment : BaseFragment(), ProfileContract.View {

    private lateinit var presenter: ProfilePresenter
    private lateinit var sharedPreference: SharedPreference

    private var isLogin: Boolean = false

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreference = SharedPreference(requireContext())
        presenter = ProfilePresenter(this)
        presenter.start()

    }

    override fun initView() {
        initProgressBar(pb_profile)
        tv_toolbarTitle.text = "PROFILE"

        isLogin = sharedPreference.getBoolean(KEY_IS_LOGIN)
        when (isLogin) {
            true -> {
                presenter.getProfile()
                showLoggedInState()
            }
            false -> {
                showNotLoginState()
            }
        }

        btn_login.setOnClickListener { startLoginActivity() }

        btn_logout.setOnClickListener { presenter.logout() }

        btn_checkOrder.setOnClickListener { startActivity(Intent(requireContext(), OrderStatusActivity::class.java)) }
    }

    override fun showProfile() {
        val username = sharedPreference.getString(KEY_USERNAME)
        val phoneNumber = sharedPreference.getString(KEY_PHONE_NUMBER)

        tv_username.text = "Nama : $username"
        tv_phoneNumber.text = "No Telepon : $phoneNumber"
    }

    override fun showLogoutSuccess(msg: String) {
        context!!.showToast(msg)

        sharedPreference.saveBoolean(KEY_IS_LOGIN, false)
        sharedPreference.saveString(KEY_USERNAME, "")
        sharedPreference.saveString(KEY_PHONE_NUMBER, "")

        startActivity(Intent(activity, MainActivity::class.java))
    }

    private fun showLoggedInState() {
        layout_loggedIn.show()
        layout_login.hide()

        showProfile()
    }

    private fun showNotLoginState() {
        layout_login.show()
        layout_loggedIn.hide()
    }

    private fun startLoginActivity() {
        startActivity(Intent(requireContext(), LoginActivity::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

}