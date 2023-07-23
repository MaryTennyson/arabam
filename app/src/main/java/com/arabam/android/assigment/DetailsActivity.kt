package com.arabam.android.assigment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.adapters.DetailAdapter
import com.arabam.android.adapters.ListingAdapter
import com.arabam.android.assigment.databinding.DetailedMainBinding
import com.arabam.android.models.Property
import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.services.AdvertAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: DetailedMainBinding
    private val BASE_URL = "https://sandbox.arabamd.com/api/v1/"
    private var detail: Details?=null
    private lateinit var detailAdapter: DetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailedMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.detailsRecyclerView.layoutManager = layoutManager

        val advertID = intent.getIntExtra("AdvertID",0)
        Log.i("test","details page (37) :${advertID}")
        if(advertID==0){
           Log.i("test","advertId =0")
        }else{
            Log.i("test","details page (46) :${advertID}")
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            Log.i("test","details page (51) :${advertID}")
            val service = retrofit.create(AdvertAPI::class.java)
            Log.i("test","details page (53) :${advertID}")
            val call = service.getDetails()
            call.enqueue(object : Callback<Details> {
                override fun onResponse(call: Call<Details>, response: Response<Details>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            detail = it
                            detail?.let {
                                 detailAdapter = DetailAdapter(detail!!.properties)
                                  binding.detailsRecyclerView.adapter = detailAdapter
                                 binding.titleView.text= detail!!.title
                                binding.locationView.text="${detail!!.location.cityName}, ${detail!!.location.townName}"
                               binding.priceView.text= detail!!.priceFormatted
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Details>, t: Throwable) {
                    t.printStackTrace()
                }


            })

        }

        }
    }






