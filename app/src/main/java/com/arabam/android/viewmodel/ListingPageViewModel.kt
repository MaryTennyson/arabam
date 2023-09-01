package com.arabam.android.viewmodel


import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.repositories.AdvertRepository


import com.arabam.android.services.APIService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ListingPageViewModel : ViewModel() {
    private val apiService = APIService
    private val newsRepository = AdvertRepository(apiService)
    private val _uiState = MutableStateFlow<GetDataState>(GetDataState.onPending)
    val uiState: StateFlow<GetDataState> = _uiState

    fun refreshData() {
        _uiState.value= GetDataState.onPending
        getAdvertDataFromAPI()
    }

   private fun getAdvertDataFromAPI() {
        viewModelScope.launch {
            newsRepository.lastestAdvert.collect {
                if (it.isEmpty()) {
                    _uiState.value = GetDataState.onFailure("Data alınamadı", "Data list boş döndü")
                } else {
                    delay(2000)
                 //   _uiState.value = GetDataState.onFailure("Data alınamadı", "Data list boş döndü")
                    _uiState.value = GetDataState.onSuccess(it)
                }
            }
        }
    }
}

sealed class GetDataState {
    data class onSuccess(val news: Any) : GetDataState()
    data class onFailure(val title: String, val exception: String) : GetDataState()
    //onFailure sealed class yapılarak aradığınız ürün bulunamadı, internet hatası gibi çeşitlendirilebilir
    object onPending : GetDataState()
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



