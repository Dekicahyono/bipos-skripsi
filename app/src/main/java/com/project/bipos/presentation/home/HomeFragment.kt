package com.project.bipos.presentation.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.bipos.R
import com.project.bipos.base.BaseFragment
import com.project.bipos.model.Banner
import com.project.bipos.model.Category
import com.project.bipos.model.Product
import com.project.bipos.presentation.category_list.ProductListByCategoryActivity
import com.project.bipos.presentation.detail.product.ProductDetailsActivity
import com.project.bipos.utils.KEY_CATEGORY
import com.project.bipos.utils.KEY_PRODUCT
import com.project.bipos.utils.hide
import com.project.bipos.utils.show
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.toolbar_custom.*


class HomeFragment : BaseFragment(), HomeContract.View {

    private lateinit var presenter: HomePresenter

    private val categoryAdapter = HomeCategoryAdapter {
        val intent = Intent(requireContext(), ProductListByCategoryActivity::class.java)
        intent.putExtra(KEY_CATEGORY, it.name)
        startActivity(intent)
    }

    private val productPopularAdapter = ProductAdapter {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra(KEY_PRODUCT, it)
        startActivity(intent)
    }

    private val bestPriceProductAdapter = BestPriceProductAdapter {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra(KEY_PRODUCT, it)
        startActivity(intent)
    }

    private val productSearchResultAdapter = ProductSearchResultAdapter {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra(KEY_PRODUCT, it)
        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = HomePresenter(this)
        presenter.start()
        presenter.getBannerList()
        presenter.getCategories()
        presenter.getProductPopularList()
        presenter.getBestPriceProductList()

        edt_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                when (s?.length) {
                    0 -> {
                        rv_searchResult.hide()
                        main_layout.show()
                    }
                    else -> {
                        presenter.getProductBySearch(s.toString())
                        main_layout.hide()
                    }
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })

    }

    override fun initView() {
        initProgressBar(pg_home)

        tv_seeMoreProductPopular.setOnClickListener { startActivityCategory("product_popular") }
        tv_seeMoreBestPrice.setOnClickListener { startActivityCategory("best_price") }
    }

    override fun showBannerList(bannerList: List<Banner>?) {
        if (bannerList != null) {
            val bannerAdapter = TopBannerAdapter(requireContext(), bannerList)
            viewPagerBanner.apply {
                adapter = bannerAdapter
                dots_indicator.setViewPager(viewPagerBanner)
            }
        }
    }

    override fun showCategoryList(categoryList: List<Category>?) {
        if (categoryList != null) {
            categoryAdapter.addItems(categoryList)
            rv_category.apply {
                layoutManager = GridLayoutManager(requireContext(), 4)
                adapter = categoryAdapter
            }
        }
    }

    override fun showProductPopularList(productList: List<Product>?) {
        if (productList != null) {
            productPopularAdapter.addItems(productList)
            rv_productPopular.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = productPopularAdapter
            }
        }
    }

    override fun showBestPriceProductList(bestPriceProductList: List<Product>?) {
        if (bestPriceProductList != null) {
            bestPriceProductAdapter.addItems(bestPriceProductList)
            rv_bestPrice.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                adapter = bestPriceProductAdapter
            }
        }
    }

    override fun showResultSearch(productList: List<Product>?) {
        rv_searchResult.show()
        if (productList != null) {
            productSearchResultAdapter.addItems(productList)
            rv_searchResult.apply {
                layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = productSearchResultAdapter
            }
        }
    }

    private fun startActivityCategory(category: String) {
        val intent = Intent(requireContext(), ProductListByCategoryActivity::class.java)
        intent.putExtra(KEY_CATEGORY, category)
        startActivity(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


}