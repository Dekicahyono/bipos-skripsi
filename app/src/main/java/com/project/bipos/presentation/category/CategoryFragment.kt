package com.project.bipos.presentation.category

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.bipos.R
import com.project.bipos.base.BaseFragment
import com.project.bipos.presentation.category_list.ProductListByCategoryActivity
import com.project.bipos.utils.DummyData
import com.project.bipos.utils.KEY_CATEGORY
import com.project.bipos.utils.showToast
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.toolbar.*


class CategoryFragment : BaseFragment(), CategoryContract.View {

    private lateinit var presenter: CategoryPresenter

    private val categoryAdapter = CategoryAdapter {
        val intent = Intent(requireContext(), ProductListByCategoryActivity::class.java)
        intent.putExtra(KEY_CATEGORY, it)
        context!!.startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = CategoryPresenter(this)
        presenter.start()
        presenter.getCategoryList(DummyData.generateCategories())

    }

    override fun initView() {
        initProgressBar(pb_category)
        tv_toolbarTitle.text = "KATEGORI"
    }

    override fun showCategoryList(categories: List<String>) {
        categoryAdapter.addItems(categories)
        rv_category.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

}