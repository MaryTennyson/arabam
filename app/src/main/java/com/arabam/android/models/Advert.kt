package com.arabam.android.models


class Advert(val id: Long,
             val title: String,
             val location: Location,
             val category: Category,
             val modelName: String,
             val price: Long,
             val priceFormatted: String,
             val date: String,
             val dateFormatted: String,
             val photo: String,
             val properties: List<Property>,) {
    data class Location(
        val cityName: String,
        val townName: String,
    )

    data class Category(
        val id: Long,
        val name: String,
    )

    data class Property(
        val name: String,
        val value: String,
    )
}



