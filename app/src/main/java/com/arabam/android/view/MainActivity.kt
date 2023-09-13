package com.arabam.android.view


import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


/*    fun showAlertDialog(title: String, exception: String) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle(title)
            .setMessage(exception)
            .setPositiveButton(
                getString(R.string.try_again),
                DialogInterface.OnClickListener { dialog, id ->
                    //     viewModel.refreshData()
                })
        alertBuilder.show()
    }

     lifecycleScope.launchWhenCreated {
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







