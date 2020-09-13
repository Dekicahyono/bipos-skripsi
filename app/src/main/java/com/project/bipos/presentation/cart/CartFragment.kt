package com.project.bipos.presentation.cart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.bipos.R
import com.project.bipos.base.BaseFragment
import com.project.bipos.db.SharedPreference
import com.project.bipos.model.Order
import com.project.bipos.presentation.delivery.DeliveryActivity
import com.project.bipos.presentation.login.LoginActivity
import com.project.bipos.utils.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.toolbar.*

class CartFragment : BaseFragment(), CartContract.View {

    private lateinit var presenter: CartPresenter
    private lateinit var sharedPreference: SharedPreference

    private var isLogin: Boolean = false

    private val cartAdapter = CartAdapter { startDeliveryActivity(it) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreference = SharedPreference(requireContext())

        presenter = CartPresenter(this)
        presenter.start()

    }

    override fun initView() {
        tv_toolbarTitle.text = "KERANJANG"

        initProgressBar(pb_cart)

        isLogin = sharedPreference.getBoolean(KEY_IS_LOGIN)
        when (isLogin) {
            true -> {
                hideLoginLayout()
                presenter.getOrderList(sharedPreference.getString(KEY_USERNAME)!!)
            }
            false -> {
                showLoginLayout()
            }
        }

        btn_login.setOnClickListener { startLoginActivity() }
    }

    override fun showOrderList(cartList: List<Order>?) {
        if (cartList != null) {
            cartAdapter.addItems(cartList)
            rv_cart.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = cartAdapter
            }
        }
    }

    override fun showEmptyListState() {
        tv_notif.show()
    }

    override fun hideEmptyListState() {
        tv_notif.hide()
    }

    private fun startDeliveryActivity(order: Order) {
        val intent = Intent(activity, DeliveryActivity::class.java)
        intent.putExtra(KEY_ORDER, order)
        startActivity(intent)
    }

    private fun startLoginActivity() {
        startActivity(Intent(requireContext(), LoginActivity::class.java))
    }

    private fun showLoginLayout() {
        layout_login.show()
        tv_notif.hide()
    }

    private fun hideLoginLayout() {
        layout_login.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


}