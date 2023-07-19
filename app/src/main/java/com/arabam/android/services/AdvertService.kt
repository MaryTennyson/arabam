package com.arabam.android.services

import com.arabam.android.models.Advert
import com.arabam.android.models.ApiResponse
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class AdvertService {
    private val advertList = ArrayList<Advert>()
    private val client = OkHttpClient()
    private val advertURL =
        "https://sandbox.arabamd.com/api/v1/listing?sort=1&sortDirection=0&take=10"

    fun run() {
        val request = Request.Builder()
            .url(advertURL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {

                    val responseData = response.toString()

                        val apiResponse = Gson().fromJson(responseData, ApiResponse::class.java)
                        advertList.addAll(apiResponse.listing)

            }

        }}



        )

    }
    fun getAdvertList(): ArrayList<Advert> {
        try {
            run()
        }catch (e:Exception){
            print(e)
        }finally {
            return advertList
        }

    }

}