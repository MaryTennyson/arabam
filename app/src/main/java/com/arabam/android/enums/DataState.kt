package com.arabam.android.enums

sealed class DataState {
    data class onSuccess(val news: Any) : DataState()
    data class onFailure(val title: String, val exception: String) : DataState() //onFailure sealed class yapılarak aradığınız ürün bulunamadı, internet hatası gibi çeşitlendirilebilir
    object onPending : DataState()
}
