package com.arabam.android.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.enums.DataState

import com.arabam.android.repositories.DetailsRepository

import dagger.hilt.android.lifecycle.HiltViewModel


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsPageViewModel @Inject constructor(val newsRepository: DetailsRepository) :
    ViewModel() {
    val _uiState = MutableStateFlow<DataState>(DataState.onPending)

    val uiState: StateFlow<DataState> = _uiState
    fun refreshData(id: Int) {
        _uiState.value = DataState.onPending
        getDetailsDataFromAPI(id)
    }

    private fun getDetailsDataFromAPI(id: Int) {
        viewModelScope.launch {
            newsRepository.lastestDetails(id).collect {
                if (it == null) {
                    _uiState.value =
                        DataState.onFailure("Detaylar Yüklenemedi", "Detaylar boş döndü")
                } else {
                    _uiState.value = DataState.onSuccess(it)
                }
            }
        }
    }
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



