package com.arabam.android.view

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import com.arabam.android.adapters.ListingAdapter
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.ActivityMainBinding

import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.services.AdvertAPI
import com.arabam.android.viewmodel.ListingPageViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : ListingPageViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var advertAdapter: ListingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))


        viewModel=ViewModelProviders.of(this).get(ListingPageViewModel::class.java)
        viewModel.refreshData()

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.includeRecyclerView.recyclerView.layoutManager = layoutManager

    //    compositeDisposable = CompositeDisposable()
        observeLiveData()
      // loadAdvertData()
    }

    private fun observeLiveData() {

    viewModel.adverts.observe(this, Observer { adverts->
        adverts?.let{
            advertAdapter.updateAdvertList(adverts)}
    })

        viewModel.advertLoading.observe(this, Observer { loading->
            loading?.let {  }
        })

        viewModel.advertLoadingError.observe(this, Observer { error->
            error?.let {

            }
        })


    }

/* private fun loadAdvertData() {

    al retrofit = Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
         .build().create<AdvertAPI>()


     compositeDisposable?.add(
         retrofit.getAdverts()
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(this::handleResponse)
     )*/
 }

/*   private fun handleResponse(adverts: List<Advert>) {
     advertList = ArrayList(adverts)
     advertList?.let {
         advertAdapter = ListingAdapter(it)
         binding.includeRecyclerView.recyclerView.adapter = advertAdapter
     }

 }*/




//https://arbstorage.mncdn.com/ilanfotograflari/2020/09/12/15456643/3c5b2dd1-856f-406f-8d10-1450061d1966_image_for_silan_15456643_{0}.jpg
/*  private fun loadPhotoData(){
for(advert in advertList!!){
 val path= advert.photo.substring(46)
Log.i("test", "${path}")
val newpath= path.replace("{0}","800x600")
Log.i("test", "${newpath}")
val retrofit = Retrofit.Builder()
   .baseUrl(BASE_PHOTO_URL)
   .addConverterFactory(GsonConverterFactory.create())
   .build()
val service = retrofit.create(AdvertAPI::class.java)
val call= service.getPhotoOfAdvert(newpath)
   call.enqueue(object : Callback<Int> {
       override fun onResponse(call: Call<Int>, response: Response<Int>) {
           if (response.isSuccessful) {
               response.body()?.let {
                   photoList?.add(it)
               }
           }
       }

       override fun onFailure(call: Call<Int>, t: Throwable) {
         println(t)
       }
   })

}


advertList?.let {
   photoList?.let { it1 -> advertAdapter = ListingAdapter(advertList!!, it1) }
   binding.includeRecyclerView.recyclerView.adapter = advertAdapter
}

}*/


/*  val service = retrofit.create(AdvertAPI::class.java)
    val call = service.getAdverts()
    call.enqueue(object : Callback<List<Advert>> {
        override fun onResponse(call: Call<List<Advert>>, response: Response<List<Advert>>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    advertList = ArrayList(it)
                    advertList?.let {
                        advertAdapter = ListingAdapter(it)
                        binding.includeRecyclerView.recyclerView.adapter = advertAdapter
                    }

                }
            }
        }

        override fun onFailure(call: Call<List<Advert>>, t: Throwable) {
            t.printStackTrace()
        }

    })*/







