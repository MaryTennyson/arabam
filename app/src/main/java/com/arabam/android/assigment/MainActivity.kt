package com.arabam.android.assigment

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager


import com.arabam.android.adapters.ListingAdapter
import com.arabam.android.assigment.databinding.ActivityMainBinding
import com.arabam.android.models.Category
import com.arabam.android.models.Location
import com.arabam.android.models.Property

import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.services.AdvertAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var advertList: ArrayList<Advert>? = null
    private lateinit var advertAdapter: ListingAdapter
    private val BASE_URL = "https://sandbox.arabamd.com/api/v1/"


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.includeRecyclerView.recyclerView.layoutManager = layoutManager
        loadAdvertData()

    }

    private fun loadAdvertData() {
        Log.i("deneme", "fonksiyona girdi")

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(AdvertAPI::class.java)
        val call = service.getAdverts()
        call.enqueue(object : Callback<List<Advert>> {
            override fun onResponse(call: Call<List<Advert>>, response: Response<List<Advert>>) {
                if (response.isSuccessful) {
                    Log.i("deneme", "deneme1")
                    response.body()?.let {
                        advertList = ArrayList(it)
                        /* for (advert: Advert in advertList!!) {
                             Log.i("deneme", "${advert.title}")
                             println(advert.title)
                         }*/
                        advertList?.let {
                            advertAdapter = ListingAdapter(it)
                            binding.includeRecyclerView.recyclerView.adapter = advertAdapter

                        }

                    }
                }
                Log.i("deneme", "fonksiyonun içinde dolanıyor")
            }

            override fun onFailure(call: Call<List<Advert>>, t: Throwable) {
                t.printStackTrace()
            }

        })
        Log.i("deneme", "fonksiyonun çıkşındaaa")
    }


}

