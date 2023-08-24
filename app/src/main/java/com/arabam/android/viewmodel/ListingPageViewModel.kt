package com.arabam.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.services.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListingPageViewModel:ViewModel() {
    private val apiService= APIService
    private val disposable= CompositeDisposable()

    val adverts= MutableLiveData<List<Advert>>()
    val advertLoadingError= MutableLiveData<Boolean>()
    val advertLoading= MutableLiveData<Boolean>()

    fun refreshData(){
        getAdvertDataFromAPI()

    }
    private fun getAdvertDataFromAPI(){
        advertLoading.value=true
        disposable.add(apiService.getDataOfAdverts()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<List<Advert>>(){
                override fun onSuccess(t: List<Advert>) {
                    adverts.value= t
                    advertLoading.value=false
                    advertLoadingError.value=false

                }

                override fun onError(e: Throwable) {
                 advertLoading.value=false
                 advertLoadingError.value=true
                }
            }))
    }
}