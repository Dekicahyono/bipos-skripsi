package com.project.bipos.presentation.delivery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.project.bipos.R
import com.project.bipos.base.BaseActivity
import com.project.bipos.db.SharedPreference
import com.project.bipos.model.Order
import com.project.bipos.presentation.detail.purchase.PurchaseDetailActivity
import com.project.bipos.presentation.main.MainActivity
import com.project.bipos.utils.*
import kotlinx.android.synthetic.main.activity_delivery.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlin.random.Random

class DeliveryActivity : BaseActivity(), DeliveryContract.View {

    private lateinit var presenter: DeliveryPresenter

    private lateinit var sharedPreference: SharedPreference
    private lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery)

        sharedPreference = SharedPreference(this)
        order = intent.getParcelableExtra(KEY_ORDER)!!

        presenter = DeliveryPresenter(this)
        presenter.start()

    }

    override fun initView() {
        initProgressBar(pb_delivery)
        tv_toolbarTitle.text = "PENGIRIMAN"

        parent_layout.setOnClickListener { hideKeyboard() }

        ArrayAdapter.createFromResource(this, R.array.deliveryType_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_deliveryType.adapter = adapter
        }

        ArrayAdapter.createFromResource(this, R.array.purchaseTyp_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_purchaseType.adapter = adapter
        }
        spinner_purchaseType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, int: Long) {
                when (pos) {
                    0 -> tv_notes.show()
                    1 -> tv_notes.hide()
                }
            }

        }

        btn_next.setOnClickListener {
            if (edt_codePos.text.isNotEmpty() && edt_province.text.isNotEmpty()
                && edt_city.text.isNotEmpty() && edt_subDistrict.text.isNotEmpty()
                && edt_address.text.isNotEmpty()
            ) {
                order.codePos = edt_codePos.text.toString()
                order.deliveryType = spinner_deliveryType.selectedItem.toString()
                order.province = edt_province.text.toString()
                order.city = edt_city.text.toString()
                order.subDistrict = edt_subDistrict.text.toString()
                order.customerAddress = edt_address.text.toString()
                order.purchaseType = spinner_purchaseType.selectedItem.toString()
                order.customerName = sharedPreference.getString(KEY_USERNAME)!!
                order.customerPhone = sharedPreference.getString(KEY_PHONE_NUMBER)!!
                order.orderNumber = (0..1000).random().toString()

                DialogUtils.showDialogOrderDetail(this, order, presenter)
            } else {
                showToast("Harap isi semua data terlebih dahulu !")
            }
        }

    }

    override fun showOrderSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}