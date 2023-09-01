package com.arabam.android.view


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android.adapters.ListingAdapter
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.ActivityMainBinding
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.viewmodel.GetDataState
import com.arabam.android.viewmodel.ListingPageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ListingPageViewModel
    private lateinit var binding: ActivityMainBinding
    private val advertAdapter = ListingAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel = ViewModelProviders.of(this).get(ListingPageViewModel::class.java)
        viewModel.refreshData()
        observeAdvert()

        binding.includeRecyclerView.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.includeRecyclerView.recyclerView.adapter = advertAdapter
    }
    private fun observeAdvert() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is GetDataState.onSuccess -> {
                            advertAdapter.updateAdvertList(it.news as List<Advert>)
                            binding.includeRecyclerView.recyclerView.visibility = View.VISIBLE
                            binding.progressBar2.visibility = View.GONE
                        }
                        is GetDataState.onPending -> {
                            binding.includeRecyclerView.recyclerView.visibility = View.GONE
                            binding.progressBar2.visibility = View.VISIBLE
                        }
                        is GetDataState.onFailure -> {
                            binding.progressBar2.visibility = View.GONE
                           showAlertDialog(it.title,it.exception)
                        }
                    }
                }
            }
        }}

    fun showAlertDialog(title: String, exception: String) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle(title)
            .setMessage(exception)
            .setPositiveButton(getString(R.string.try_again), DialogInterface.OnClickListener { dialog, id ->
                viewModel.refreshData()
            })
        alertBuilder.show()
    }

        /* lifecycleScope.launchWhenCreated {
             viewModel.adverts.collect {
                 advertAdapter.updateAdvertList(it)
             }
         }
         lifecycleScope.launchWhenCreated {
             viewModel.advertLoading.collect {
                 if (it) {
                     binding.includeRecyclerView.recyclerView.visibility = View.GONE
                     binding.progressBar2.visibility = View.VISIBLE
                 } else {
                     binding.includeRecyclerView.recyclerView.visibility = View.VISIBLE
                     binding.progressBar2.visibility = View.GONE
                 }
             }
         }
         lifecycleScope.launchWhenCreated {
             viewModel.advertLoadingError.collect {
                 if (it) {
                     Toast.makeText(this@MainActivity, "Bir Hata Meydana Geldi", Toast.LENGTH_LONG)
                         .show()
                 }
             }
         }*/

}
/*  private fun observeLiveData() {
      viewModel.advertLoading.observe(this, Observer { loading ->
          loading?.let { }
      })
      viewModel.advertLoadingError.observe(this, Observer { error ->
          error?.let {

          }
      })
  }*/


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







