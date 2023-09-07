package com.arabam.android.repositories

import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.services.APIService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AdvertRepository @Inject constructor(val dataService: APIService) {
    val getDataOfAdvert: Flow<List<Advert>> = flow {
        emit(dataService.getDataOfAdverts())
    }
}