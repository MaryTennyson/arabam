package com.arabam.android.view


import android.content.DialogInterface
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android.adapters.DetailAdapter
import com.arabam.android.adapters.SliderAdapter
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.DetailedMainBinding
import com.arabam.android.enums.DataState
import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.viewmodel.DetailsPageViewModel
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsPageViewModel //(?) //constructor içinde class interface vb gönderilmeli, string vs değil
    private lateinit var binding: DetailedMainBinding

    private var detailAdapter = DetailAdapter(arrayListOf())

    private lateinit var sliderView: SliderView

    lateinit var sliderAdapter: SliderAdapter
    var advertID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailedMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.detailsRecyclerView.adapter = detailAdapter

        advertID = intent.getIntExtra("AdvertID", 0)
        viewModel = ViewModelProviders.of(this).get(DetailsPageViewModel::class.java)

        sliderView = binding.imageslider

        binding.descriptions.visibility = View.GONE
        binding.detailsRecyclerView.visibility = View.VISIBLE
        binding.detailslayout.visibility = View.GONE


        if (advertID == 0) {
            println("bir hata meydana geldi")
        } else {
            viewModel = ViewModelProviders.of(this).get(DetailsPageViewModel::class.java)
            viewModel.refreshData(advertID)
            observeDetails()
        }
    }

    private fun observeDetails() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {

                    when (it) {
                        is DataState.onPending -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.detailslayout.visibility = View.GONE
                        }
                        is DataState.onSuccess -> {
                            val detail = it.news as Details
                            detailAdapter.updateAdvertList(detail.properties)
                            binding.titleView.text = detail!!.title
                            binding.locationView.text =
                                "${detail!!.location.cityName}, ${detail!!.location.townName}"
                            binding.priceView.text = detail!!.priceFormatted
                            binding.userName.text = detail!!.userInfo.nameSurname
                            binding.userPhoneNumber.text = "+${detail!!.userInfo.phone}"
                            binding.descriptions.text = Html.fromHtml(detail!!.text).toString()
                            binding.descriptions.movementMethod =
                                android.text.method.ScrollingMovementMethod()
                            sliderAdapter = SliderAdapter(detail.photos)
                            sliderView.setSliderAdapter(sliderAdapter)
                            binding.progressBar.visibility = View.GONE
                            binding.detailslayout.visibility = View.VISIBLE
                        }
                        is DataState.onFailure -> {
                            binding.progressBar.visibility = View.GONE
                            binding.detailslayout.visibility = View.GONE
                            showAlertDialog(it.title, it.exception)

                        }
                    }
                }
            }
        }
    }

    fun showAlertDialog(title: String, exception: String) {
        val alertBuilder = AlertDialog.Builder(this)
        alertBuilder.setTitle(title)
            .setMessage(exception)
            .setPositiveButton(
                getString(R.string.try_again),
                DialogInterface.OnClickListener { dialog, id ->
                    viewModel.refreshData(advertID)
                })
        alertBuilder.show()
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


/*   private fun observeLiveData(){
       viewModel.details.observe(this, Observer { detail->
           detail?.let {
               detailAdapter.updateAdvertList(detail.properties)
               binding.titleView.text = detail!!.title
               binding.locationView.text =
                   "${detail!!.location.cityName}, ${detail!!.location.townName}"
               binding.priceView.text = detail!!.priceFormatted
               binding.userName.text=detail!!.userInfo.nameSurname
               binding.userPhoneNumber.text="+${detail!!.userInfo.phone}"
               binding.descriptions.text= Html.fromHtml(detail!!.text).toString()
               binding.descriptions.movementMethod=  android.text.method.ScrollingMovementMethod()

               sliderAdapter = SliderAdapter(detail.photos)
               sliderView.setSliderAdapter(sliderAdapter)
           }
       } )


   }




 /*  private fun hdleResponse(details: Details) {
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

   }*/

   fun onClickedAdvertInformation(view: View) {
       binding.descriptions.visibility = View.GONE
       binding.detailsRecyclerView.visibility = View.VISIBLE

   }

   fun onClickedAdvertDescription(view: View) {
       binding.detailsRecyclerView.visibility = View.GONE
       binding.descriptions.visibility = View.VISIBLE


   }
}


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