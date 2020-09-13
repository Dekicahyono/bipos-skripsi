package com.project.bipos.utils

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView
import com.project.bipos.presentation.main.MainActivity
import com.project.bipos.R
import com.project.bipos.model.Order
import com.project.bipos.presentation.delivery.DeliveryPresenter
import java.util.*

object DialogUtils {

    fun showDialogOrderDetail(activity: Activity, order: Order, presenter: DeliveryPresenter) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_order_detail)
        dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        Objects.requireNonNull(dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT)))

        dialog.findViewById<TextView>(R.id.tv_productName).text = order.productName
        dialog.findViewById<TextView>(R.id.tv_orderNo).text = order.orderNumber
        dialog.findViewById<TextView>(R.id.tv_customerName).text = order.customerName
        dialog.findViewById<TextView>(R.id.tv_customerAddress).text = order.customerAddress
        dialog.findViewById<TextView>(R.id.tv_customerPhone).text = order.customerPhone
        dialog.findViewById<TextView>(R.id.tv_totalPrice).text = "Rp. ${order.totalPrice}"
        dialog.findViewById<TextView>(R.id.tv_purchaseType).text = order.purchaseType

        val btnConfirm = dialog.findViewById<RelativeLayout>(R.id.btn_confirm)
        btnConfirm.setOnClickListener {
            dialog.dismiss()

            presenter.deleteFromOrder(order)
//            activity.showToast("Berhasil Memesan barang")
//            activity.startActivity(Intent(activity, MainActivity::class.java))
        }

        dialog.show()
    }
}