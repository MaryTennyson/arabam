package com.arabam.android.services

import android.util.Log
import com.arabam.android.adapters.ListingAdapter
import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.models.listingmodels.Advert
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIService {/*
    private val BASE_URL = "https://sandbox.arabamd.com/api/v1/"
     var advertList:  ArrayList<Advert>?= null
   var detailsList: ArrayList<Details>?= null


     fun loadAdvertData( ) {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AdvertAPI::class.java)
        val call = service.getAdverts()
        call.enqueue(object : Callback<List<Advert>> {
            override fun onResponse(call: Call<List<Advert>>, response: Response<List<Advert>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        advertList = ArrayList(it)
                    }
                }
            }
            override fun onFailure(call: Call<List<Advert>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }



     fun loadDetailsData(advertID: Int): ArrayList<Details>? {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AdvertAPI::class.java)
        val call = service.getDetails(advertID)
        call.enqueue(object : Callback<Details> {
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        detailsList = ArrayList(it)

                    }
                }
            }

            override fun onFailure(call: Call<List<Details>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    return detailsList;
    }


*/
}