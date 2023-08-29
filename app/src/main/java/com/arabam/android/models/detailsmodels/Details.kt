package com.arabam.android.models.detailsmodels

import com.arabam.android.models.Category
import com.arabam.android.models.Location
import com.arabam.android.models.Property

data class Details(
    val category: Category,
    val date: String,
    val dateFormatted: String,
    val id: Int,
    val location: Location,
    val modelName: String,
    var photos: List<String>,
    val price: Int,
    val priceFormatted: String,
    val properties: List<Property>,
    val text: String,
    val title: String,
    val userInfo: UserInfo
)