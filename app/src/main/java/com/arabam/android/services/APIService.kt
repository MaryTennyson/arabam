package com.arabam.android.services

import android.util.Log
import com.arabam.android.adapters.ListingAdapter
import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.models.listingmodels.Advert
import io.reactivex.Single
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIService {
    private val BASE_URL = "https://sandbox.arabamd.com/api/v1/"
    private val api= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create<AdvertAPI>()

    fun getDataOfAdverts(): Single<List<Advert>> {
        return  api.getAdverts()
    }
    fun getDataOfDetails(advertID:Int): Single<Details> {
        return api.getDetails(advertID)
    }






}