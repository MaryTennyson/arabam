package com.arabam.android.services



import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.models.listingmodels.Advert
import retrofit2.Call
import retrofit2.http.GET

interface AdvertAPI {

@GET("listing?sort=1&sortDirection=0&take=10")
 fun getAdverts(): Call<List<Advert>>


 @GET("detail?id=15948868") // TODO ID DEĞİŞTİRİLEBİLİR OLMALI
 fun getDetails(): Call<List<Details>>




}