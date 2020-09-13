package com.project.bipos.presentation.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.project.bipos.R
import com.project.bipos.model.Banner

class TopBannerAdapter(private val context: Context, private val bannerList: List<Banner>) :
    PagerAdapter() {

    override fun isViewFromObject(view: View, o: Any): Boolean = view == o

    override fun getCount(): Int = bannerList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        try {
            val banner: Banner = bannerList[position]
            val view = LayoutInflater.from(context).inflate(R.layout.item_post, null)
            val imageBanner = view.findViewById<ImageView>(R.id.img_post)

            Glide.with(context)
                .load(banner.imageUrl)
                .into(imageBanner)

            container.addView(view)
            return view
        } catch (e: Exception) {
            Log.e("BannerAdapterErr", e.localizedMessage!!)
        }

        return container
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}