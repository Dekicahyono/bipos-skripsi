package com.project.bipos.presentation.detail.purchase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.bipos.R
import com.project.bipos.model.Order
import com.project.bipos.utils.DialogUtils
import kotlinx.android.synthetic.main.activity_purchase_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class PurchaseDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase_detail)

        initView()

    }

    private fun initView() {
        tv_toolbarTitle.text = "PILIH PEMBAYARAN"

        val order = Order(
            "nama produk",
            "nomer order",
            "nama customer",
            "alamat customer",
            "nomer hp customer",
            "2000",
            "transfer"
        )
//        btn_confirm.setOnClickListener { DialogUtils.showDialogOrderDetail(this, order, ) }
    }
}