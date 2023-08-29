package com.arabam.android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.services.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class DetailsPageViewModel() :ViewModel(){
    private var advertID:Int=0
    private val apiService= APIService
    private val disposable= CompositeDisposable()

    val details= MutableLiveData<Details>()


    fun refreshData(newadvertID: Int){
        advertID=newadvertID
   //     getDetailsDataFromAPI()
    }

  /*  private fun getDetailsDataFromAPI() {
        println("getdetailse geldi")
        disposable.add(apiService.getDataOfDetails(advertID)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object:DisposableSingleObserver<Details>(){
                override fun onSuccess(t: Details) {
                   details.value=t
                }

                override fun onError(e: Throwable) {
                   e.printStackTrace()
                }


            }))

    }*/

}

