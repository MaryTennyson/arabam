package com.arabam.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.repositories.AdvertRepository
import com.arabam.android.services.APIService

import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow


import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

import kotlinx.coroutines.launch


class ListingPageViewModel() : ViewModel() {
    private val apiService = APIService
    private val disposable = CompositeDisposable()
    private val newsRepository= AdvertRepository(apiService)

    val adverts: MutableStateFlow<List<Advert>> = MutableStateFlow<List<Advert>>(listOf())  //?

    val advertLoadingError = MutableLiveData<Boolean>()
    val advertLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getAdvertDataFromAPI()
    }

    fun getAdvertDataFromAPI() {
        viewModelScope.launch {
            newsRepository.lastestAdvert
                .collect {
                    adverts.value = it }
        }
    }




    /* private fun getAdvertDataFromAPI() {
         advertLoading.value = true
         disposable.add(apiService.getDataOfAdverts()
             .subscribeOn(Schedulers.newThread())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeWith(object : DisposableSingleObserver<List<Advert>>() {
                 override fun onSuccess(t: List<Advert>) {
                     adverts.value = t
                     advertLoading.value = false
                     advertLoadingError.value = false
                 }
                 override fun onError(e: Throwable) {
                     advertLoading.value = false
                     advertLoadingError.value = true
                 }
             })
         )
     }*/
}