package com.arabam.android.repositories

import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.services.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class AdvertRepository ( val dataService: APIService ){

    val lastestAdvert: Flow<List<Advert>> = flow {
        emit(dataService.getDataOfAdverts())

    }

}