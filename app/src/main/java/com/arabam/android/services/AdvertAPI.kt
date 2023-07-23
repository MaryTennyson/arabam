package com.arabam.android.services



import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.models.listingmodels.Advert
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AdvertAPI {

@GET("listing?sort=1&sortDirection=0&take=10")
 fun getAdverts(): Call<List<Advert>>

// @GET("detail?id={advertID}")
 //fun getDetails(@Path("advertID") advertID: Int): Call<Details>

 @GET("detail?id=15943485")
 fun getDetails(): Call<Details>



}