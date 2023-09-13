package com.arabam.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.enums.DataState
import com.arabam.android.repositories.AdvertRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingPageViewModel @Inject constructor(val advertRepository: AdvertRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow<DataState>(DataState.onPending)
    val uiState: StateFlow<DataState> = _uiState
    fun refreshData() {
        _uiState.value = DataState.onPending
        getAdvertDataFromAPI()
    }

    private fun getAdvertDataFromAPI() {
        viewModelScope.launch {
            advertRepository.getDataOfAdvert.collect {
                if (it.isEmpty()) {
                    _uiState.value = DataState.onFailure("Data alınamadı", "Data list boş döndü")
                } else {
                    delay(1000)
                    //   _uiState.value = GetDataState.onFailure("Data alınamadı", "Data list boş döndü")
                    _uiState.value = DataState.onSuccess(it)
                }
            }
        }
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



