package com.arabam.android.models.listingmodels

import com.arabam.android.models.Category
import com.arabam.android.models.Location
import com.arabam.android.models.Property
import com.google.gson.annotations.SerializedName

data class Advert(
    @SerializedName("category")
    val category: Category,
    val date: String,
    val dateFormatted: String,
    val id: Int,
    val location: Location,
    val modeName: String,
    val photo: String,
    val price: Int,
    val priceFormatted: String,
    val properties: List<Property>,
    val title: String
)