package com.arabam.android.assigment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html

import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.adapters.DetailAdapter
import com.arabam.android.adapters.SliderAdapter


import com.arabam.android.assigment.databinding.DetailedMainBinding
import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.services.AdvertAPI
import com.smarteist.autoimageslider.SliderView

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.schedulers.Schedulers
import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: DetailedMainBinding
    private val BASE_URL = "https://sandbox.arabamd.com/api/v1/"
    private var detail: Details? = null
    private lateinit var detailAdapter: DetailAdapter
    private lateinit var imagesUrl: ArrayList<String>
    private lateinit var sliderView: SliderView
    lateinit var sliderAdapter: SliderAdapter


    //Disposable
    private var compositeDisposable: CompositeDisposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailedMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.detailsRecyclerView.layoutManager = layoutManager
        sliderView = binding.imageslider

        compositeDisposable = CompositeDisposable()
        binding.descriptions.visibility = View.GONE

        binding.detailsRecyclerView.visibility = View.VISIBLE

        val advertID = intent.getIntExtra("AdvertID", 0)

        if (advertID == 0) {
            println ("bir hata meydana geldi")
        } else {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create<AdvertAPI>()

            compositeDisposable?.add(
                retrofit.getDetails(advertID)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse)
            )


/*   val service = retrofit.create(AdvertAPI::class.java)


    val call = service.getDetails(advertID)
  call.enqueue(object : Callback<Details> {
      override fun onResponse(call: Call<Details>, response: Response<Details>) {
          if (response.isSuccessful) {
              response.body()?.let {
                  detail = it
                  detail?.let {
                      detailAdapter = DetailAdapter(detail!!.properties)
                      binding.detailsRecyclerView.adapter = detailAdapter
                      binding.titleView.text = detail!!.title
                      binding.locationView.text =
                          "${detail!!.location.cityName}, ${detail!!.location.townName}"
                      binding.priceView.text = detail!!.priceFormatted
                      binding.userName.text=detail!!.userInfo.nameSurname
                      binding.userPhoneNumber.text="+${detail!!.userInfo.phone}"
                      binding.descriptions.text= Html.fromHtml(detail!!.text).toString()
                      println(Html.fromHtml(detail!!.text).toString())
                      imagesUrl = ArrayList(it.photos)
                      sliderAdapter = SliderAdapter(imagesUrl)

                      sliderView.setSliderAdapter(sliderAdapter)


                  }
              }
          }
      }

      override fun onFailure(call: Call<Details>, t: Throwable) {
          t.printStackTrace()
      }


  })

}*/
        }
    }

    private fun handleResponse(details: Details) {
        detail = details
        detail?.let {
            detailAdapter = DetailAdapter(detail!!.properties)
            binding.detailsRecyclerView.adapter = detailAdapter
            binding.titleView.text = detail!!.title
            binding.locationView.text =
                "${detail!!.location.cityName}, ${detail!!.location.townName}"
            binding.priceView.text = detail!!.priceFormatted
            binding.userName.text=detail!!.userInfo.nameSurname
            binding.userPhoneNumber.text="+${detail!!.userInfo.phone}"
            binding.descriptions.text= Html.fromHtml(detail!!.text).toString()
            println(Html.fromHtml(detail!!.text).toString())
            imagesUrl = ArrayList(it.photos)
            sliderAdapter = SliderAdapter(imagesUrl)

            sliderView.setSliderAdapter(sliderAdapter)


        }

    }

    fun onClickedAdvertInformation(view: View) {
        binding.descriptions.visibility = View.GONE
        binding.detailsRecyclerView.visibility = View.VISIBLE

    }

    fun onClickedAdvertDescription(view: View) {

        binding.detailsRecyclerView.visibility = View.GONE
        binding.descriptions.visibility = View.VISIBLE


    }
}






