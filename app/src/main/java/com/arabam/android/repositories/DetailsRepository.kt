package com.arabam.android.repositories


import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.services.APIService
import kotlinx.coroutines.flow.Flow


import kotlinx.coroutines.flow.flow

class DetailsRepository(val dataservice: APIService, val id: Int) {

    val lastestDetails: Flow<Details> = flow {
        emit(dataservice.getDataOfDetails(id))
    }
}