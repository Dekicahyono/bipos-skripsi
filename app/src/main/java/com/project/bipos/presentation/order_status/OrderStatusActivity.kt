package com.project.bipos.presentation.order_status

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.bipos.R
import com.project.bipos.base.BaseActivity
import com.project.bipos.db.SharedPreference
import com.project.bipos.model.Order
import com.project.bipos.utils.KEY_USERNAME
import com.project.bipos.utils.showToast
import kotlinx.android.synthetic.main.activity_order_status.*
import kotlinx.android.synthetic.main.toolbar.*

class OrderStatusActivity : BaseActivity(), OrderStatusContract.View {

    private lateinit var presenter: OrderStatusPresenter

    private val orderStatusAdapter = OrderStatusAdapter {
        showToast(it.productName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_status)

        val sharedPreference = SharedPreference(this)
        val username = sharedPreference.getString(KEY_USERNAME)!!

        presenter = OrderStatusPresenter(this)
        presenter.start()
        presenter.getOrderStatusList(username)

    }

    override fun initView() {
        initProgressBar(pb_orderStatus)
        tv_toolbarTitle.text = "Order Status"
    }

    override fun showOrderStatusList(orderList: List<Order>?) {
        if (orderList != null) {
            orderStatusAdapter.addItems(orderList)
            rv_orderStatus.apply {
                layoutManager =
                    LinearLayoutManager(this@OrderStatusActivity, RecyclerView.VERTICAL, false)
                adapter = orderStatusAdapter
            }
        }
    }
}