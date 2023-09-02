package com.arabam.android.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.enums.DataState

import com.arabam.android.repositories.DetailsRepository
import com.arabam.android.services.APIService

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsPageViewModel(val newadvertID: Int) :ViewModel(){

    private val apiService= APIService
    private val newsRepository= DetailsRepository(apiService,newadvertID)
    val _uiState= MutableStateFlow<DataState>(DataState.onPending)

    val uiState: StateFlow<DataState> = _uiState
    fun refreshData(){
        _uiState.value= DataState.onPending
        getDetailsDataFromAPI()
    }

    private fun getDetailsDataFromAPI() {
        viewModelScope.launch {
            newsRepository.lastestDetails.collect {
                if(it==null){
                    _uiState.value= DataState.onFailure("Detaylar Yüklenemedi", "Detaylar boş döndü")
                }else{
                    _uiState.value=DataState.onSuccess(it)
                }
        }
    }
}}

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



