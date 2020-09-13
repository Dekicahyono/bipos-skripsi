package com.project.bipos.presentation.detail.product

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.project.bipos.R
import com.project.bipos.base.BaseActivity
import com.project.bipos.db.SharedPreference
import com.project.bipos.model.Order
import com.project.bipos.model.Product
import com.project.bipos.presentation.delivery.DeliveryActivity
import com.project.bipos.utils.*
import kotlinx.android.synthetic.main.activity_detail_product.*
import kotlinx.android.synthetic.main.activity_detail_product.img_product

class ProductDetailsActivity : BaseActivity(), ProductDetailsContract.View {

    private lateinit var presenter: ProductDetailsPresenter
    private lateinit var sharedPreferences: SharedPreference

    private lateinit var order: Order
    private var isLoggedIn: Boolean = false
    private var counter: Int = 1
    private var totalPrice: Int = 0
    private var isAddToCart: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_product)

        sharedPreferences = SharedPreference(this)
        isLoggedIn = sharedPreferences.getBoolean(KEY_IS_LOGIN)

        val product = intent.getParcelableExtra<Product>(KEY_PRODUCT)!!

        presenter = ProductDetailsPresenter(this)
        presenter.start()
        presenter.getProductDetails(product)

    }

    override fun initView() {
        initProgressBar(pb_detail)

        ic_back.setOnClickListener { finish() }
    }

    override fun showProductsDetails(product: Product) {
        Glide.with(this)
            .load(product.image)
            .into(img_product)
        tv_productName.text = product.name
        tv_brandName.text = product.brand
        tv_productDesc.text = product.description
        totalPrice =  product.price
        tv_totalPrice.text  = Converter.rupiah(product.price.toDouble())

        btn_plus.setOnClickListener {
            counter++
            tv_count.text = counter.toString()

            totalPrice = product.price.times(counter)
            tv_totalPrice.text = Converter.rupiah(totalPrice.toDouble())
        }

        btn_minus.setOnClickListener {
            if (counter > 1) {
                counter--
                tv_count.text = counter.toString()

                totalPrice  = totalPrice.minus(product.price)
                tv_totalPrice.text = Converter.rupiah(totalPrice.toDouble())
            }
        }

        btn_chat.setOnClickListener { sendMessageToWhatsApp(product.phoneNumber!!) }

        btn_buy.setOnClickListener {
            when (isLoggedIn) {
                true -> {
                    order = Order()
                    order.image = product.image
                    order.productName = product.name!!
                    order.totalPrice = totalPrice
                    order.productId = product.id
                    order.customerName = sharedPreferences.getString(KEY_USERNAME)!!
                    presenter.postProductToCart(order)

                    startDeliveryActivity(order)
                }
                else -> showToast("Belum masuk nih,Yuk Login dulu!")
            }
        }

        ic_addToCart.setOnClickListener {
            when (isLoggedIn) {
                true -> {
                    order = Order()
                    order.image = product.image
                    order.productName = product.name!!
                    order.totalPrice = totalPrice
                    order.productId = product.id
                    order.customerName = sharedPreferences.getString(KEY_USERNAME)!!

                    isAddToCart = true
                    presenter.postProductToCart(order)
                }
                else -> showToast("Belum masuk nih,Yuk Login dulu!")
            }
        }
    }

    override fun showAddToCartSuccess(msg: String) {
        when (isAddToCart) {
            true -> showToast(msg)
        }
    }

    private fun sendMessageToWhatsApp(number: String) {
        try {
            val uri = Uri.parse("smsto:$number")
            val intent = Intent(Intent.ACTION_SENDTO, uri)

            intent.`package` = "com.whatsapp"
            startActivity(Intent.createChooser(intent, ""))
        } catch (e: PackageManager.NameNotFoundException) {
            showToast("WhatsApp tidak terinstall")
        }
    }

    private fun startDeliveryActivity(order: Order) {
        val intent = Intent(this, DeliveryActivity::class.java)
        intent.putExtra(KEY_ORDER, order)
        startActivity(intent)
    }

}