package com.arabam.android.services


import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.models.listingmodels.Advert
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.flow.Flow

import retrofit2.http.GET

import retrofit2.http.Query

interface AdvertAPI {

    @GET("listing?sort=1&sortDirection=0&take=15")
    suspend fun getAdverts(): List<Advert>

    @GET("detail")
    suspend fun getDetails(@Query("id") advertID: Int): Details
}