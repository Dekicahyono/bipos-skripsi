package com.project.bipos.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.project.bipos.R
import com.project.bipos.db.SharedPreference
import com.project.bipos.presentation.cart.CartFragment
import com.project.bipos.presentation.category.CategoryFragment
import com.project.bipos.presentation.home.HomeFragment
import com.project.bipos.presentation.profile.ProfileFragment
import com.project.bipos.utils.KEY_IS_LOGIN
import com.project.bipos.utils.KEY_USERNAME
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val homeFragment: Fragment = HomeFragment()
    private val categoryFragment: Fragment = CategoryFragment()
    private val cartFragment: Fragment = CartFragment()
    private val profileFragment: Fragment = ProfileFragment()
    private val fm = supportFragmentManager
    private var active = homeFragment

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_home -> {
                fm.beginTransaction().hide(active).show(homeFragment).commit()
                active = homeFragment

                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_category -> {
                fm.beginTransaction().hide(active).show(categoryFragment).commit()
                active = categoryFragment

                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_cart -> {
                fm.beginTransaction().hide(active).show(cartFragment).commit()
                active = cartFragment

                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                fm.beginTransaction().hide(active).show(profileFragment).commit()
                active = profileFragment

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    private lateinit var presenter: MainPresenter
    private lateinit var sharedPreference: SharedPreference

    private var isLogin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        sharedPreference = SharedPreference(this)
        presenter = MainPresenter(this)

        isLogin = sharedPreference.getBoolean(KEY_IS_LOGIN)
        if (isLogin) {
            val username = sharedPreference.getString(KEY_USERNAME)!!

            presenter.checkUserLogin(isLogin)
            presenter.getCartCounter(username)
        }

        fm.beginTransaction().add(R.id.main_container, profileFragment, "4").hide(profileFragment).commit()
        fm.beginTransaction().add(R.id.main_container, cartFragment, "3").hide(cartFragment).commit()
        fm.beginTransaction().add(R.id.main_container, categoryFragment, "2").hide(categoryFragment).commit()
        fm.beginTransaction().add(R.id.main_container, homeFragment, "1").commit()

    }

    override fun showCartBadge() {
        bottomNavigation.getOrCreateBadge(R.id.nav_cart).isVisible = true
    }

    override fun hideCartBadge() {
        bottomNavigation.removeBadge(R.id.nav_cart)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    override fun onResume() {
        super.onResume()

        isLogin = sharedPreference.getBoolean(KEY_IS_LOGIN)
        presenter.checkUserLogin(isLogin)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}