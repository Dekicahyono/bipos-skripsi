package com.project.bipos.presentation.category_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.project.bipos.R
import com.project.bipos.base.BaseActivity
import com.project.bipos.model.Product
import com.project.bipos.presentation.detail.product.ProductDetailsActivity
import com.project.bipos.utils.KEY_CATEGORY
import com.project.bipos.utils.KEY_PRODUCT
import com.project.bipos.utils.showToast
import kotlinx.android.synthetic.main.activity_product_list_by_category.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductListByCategoryActivity : BaseActivity(), ProductListByCategoryContract.View {

    private lateinit var presenter: ProductListByCategoryPresenter

    private lateinit var category: String

    private val productAdapter = ProductAdapter {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra(KEY_PRODUCT, it)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list_by_category)

        category = intent.getStringExtra(KEY_CATEGORY)!!

        presenter = ProductListByCategoryPresenter(this)
        presenter.start()
        presenter.getProductListByCategory(category)

    }

    override fun initView() {
        initProgressBar(pb_products)
        tv_toolbarTitle.text = category
    }

    override fun showProductListByCategory(productList: List<Product>?) {
        if (productList != null) {
            productAdapter.addItems(productList)
            rv_products.apply {
                layoutManager = GridLayoutManager(this@ProductListByCategoryActivity, 2)
                adapter = productAdapter
            }
        }
    }
}