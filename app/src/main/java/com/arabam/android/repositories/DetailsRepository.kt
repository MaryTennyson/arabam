package com.arabam.android.repositories


import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.services.APIService
import kotlinx.coroutines.flow.Flow


import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class DetailsRepository @Inject constructor(val dataservice: APIService) {
    fun lastestDetails(id :Int): Flow<Details> = flow {
        emit(dataservice.getDataOfDetails(id))
    }
}