package com.arabam.android.services




import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.models.listingmodels.Advert
import io.reactivex.Observable

import retrofit2.http.GET

import retrofit2.http.Query

interface AdvertAPI {

@GET("listing?sort=1&sortDirection=0&take=15")
 fun getAdverts(): Observable<List<Advert>>


 @GET("detail")
  fun getDetails(@Query("id") advertID: Int): Observable<Details>

 //@GET("detail?id=15456643")
 //fun getDetails(): Call<Details>


}