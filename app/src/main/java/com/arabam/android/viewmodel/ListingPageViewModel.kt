package com.arabam.android.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.repositories.AdvertRepository
import com.arabam.android.services.APIService
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect


import kotlinx.coroutines.launch


class ListingPageViewModel() : ViewModel() {
    private val apiService = APIService
    private val newsRepository = AdvertRepository(apiService)

    private val _uiState = MutableStateFlow(getDataState.onSuccess(arrayListOf()))

    val uiState:StateFlow<getDataState> = _uiState

    fun refreshData() {
        getAdvertDataFromAPI()
    }
    fun getAdvertDataFromAPI() {
        viewModelScope.launch {
            newsRepository.lastestAdvert.collect {
                if(it.isEmpty()){
                  //  _uiState.value = getDataState.onFailure("deneme")//TODO IT GIVES AN ERROR
                }else{
                    _uiState.value= getDataState.onSuccess(it)
                }
            }
        }
    }
}

sealed class getDataState {
    data class onSuccess(val news: List<Advert>): getDataState()
    data class onFailure(val exception: String): getDataState()
    object onPending: getDataState()


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



