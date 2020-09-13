package com.project.bipos.presentation.home

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.project.bipos.model.Banner
import com.project.bipos.model.Product
import com.project.bipos.utils.DummyData

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    private val bannersReference = FirebaseDatabase.getInstance().getReference("banners")
    private var bannerList: MutableList<Banner> = mutableListOf()

    private val productsReference = FirebaseDatabase.getInstance().getReference("products").child("products_popular")
    private var productsList: MutableList<Product> = mutableListOf()

    private val bestPriceReference = FirebaseDatabase.getInstance().getReference("products").child("best_price")
    private var bestPriceProductList: MutableList<Product> = mutableListOf()

    override fun getBannerList() {
        view.showLoading()
        bannersReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                view.hideLoading()
                for (postSnapshot in snapshot.children) {
                    val banner = postSnapshot.getValue(Banner::class.java)
                    bannerList.add(banner!!)
                }

                view.showBannerList(bannerList)
            }

        })
    }

    override fun getCategories() {
        view.showCategoryList(DummyData.generateCategory())
    }

    override fun getProductPopularList() {
        view.showLoading()
        val reference = FirebaseDatabase.getInstance().reference
        val query = reference.child("products").orderByChild("type").equalTo("product_popular")
        query.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                view.hideLoading()
                for (postSnapshot in snapshot.children) {
                    val product = postSnapshot.getValue(Product::class.java)
                    productsList.add(product!!)
                }

                view.showProductPopularList(productsList)
            }

        })
    }

    override fun getBestPriceProductList() {
        view.showLoading()
        val reference = FirebaseDatabase.getInstance().reference
        val query = reference.child("products").orderByChild("type").equalTo("best_price")
        query.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                view.hideLoading()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                view.hideLoading()
                for (postSnapshot in snapshot.children) {
                    val product = postSnapshot.getValue(Product::class.java)
                    bestPriceProductList.add(product!!)
                }

                view.showBestPriceProductList(bestPriceProductList)
            }

        })
    }

    override fun getProductBySearch(search: String) {
        val reference = FirebaseDatabase.getInstance().reference
        val query = reference.child("products").orderByChild("name").startAt(search).endAt(search+"\uf8ff")
        val productsList: MutableList<Product> = mutableListOf()

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (data in dataSnapshot.children) {
                    val product = data.getValue(Product::class.java)
                    productsList.add(product!!)
                }

                view.showResultSearch(productsList)
            }

        })
    }

    override fun start() {
        view.initView()
    }
}