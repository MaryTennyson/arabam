package com.arabam.android.services


import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.models.listingmodels.Advert

import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class APIService @Inject constructor(val api: AdvertAPI) {

    suspend fun getDataOfAdverts(): List<Advert> {
        return api.getAdverts()
    }

    suspend fun getDataOfDetails(advertID: Int): Details {
        return api.getDetails(advertID)
    }

}
/* private val BASE_URL = "https://sandbox.arabamd.com/api/v1/"
 private val api = Retrofit.Builder()
     .baseUrl(BASE_URL)
     .addConverterFactory(GsonConverterFactory.create())
//       .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
     .build().create<AdvertAPI>()

 suspend fun getDataOfAdverts(): List<Advert> {
     return api.getAdverts()
 }

 suspend fun getDataOfDetails(advertID: Int): Details {
     return api.getDetails(advertID)
 }

}*/