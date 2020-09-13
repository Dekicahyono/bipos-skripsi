package com.project.bipos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: String? = "",
    val image: String? = "",
    val name: String? = "",
    val brand: String? = "",
    val price: Int = 0,
    val phoneNumber: String? = "",
    val description: String? = ""
): Parcelable