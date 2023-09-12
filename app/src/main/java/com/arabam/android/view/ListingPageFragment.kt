package com.arabam.android.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.arabam.android.adapters.ListingAdapter
import com.arabam.android.assigment.databinding.FragmentListingPageBinding
import com.arabam.android.enums.DataState
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.viewmodel.ListingPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


class ListingPageFragment : Fragment() {
    //private val viewModel: ListingPageViewModel by viewModels()
    private var _binding: FragmentListingPageBinding? = null
    private val binding get() = _binding!!
    private val advertAdapter = ListingAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  viewModel.refreshData()
        observeAdvert()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListingPageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    private fun observeAdvert() {

/*  lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.STARTED) {
          viewMod.uiState.collect {
              when (it) {
                  is DataState.onSuccess -> {
                      advertAdapter.updateAdvertList(it.news as List<Advert>)

                      binding.listingRecyclerView.visibility = View.VISIBLE
                      binding.progressBar.visibility = View.GONE
                  }
                  is DataState.onPending -> {
                      binding.listingRecyclerView.visibility = View.GONE
                      binding.progressBar.visibility = View.VISIBLE
                  }
                  is DataState.onFailure -> {
                      binding.progressBar.visibility = View.GONE
                      //  showAlertDialog(it.title, it.exception)
                  }
              }
          }
      }
  }*/
}

/* fun showAlertDialog(title: String, exception: String) {
   val alertBuilder = AlertDialog.Builder(this) //TODO ACCORDING TO INTERNET BEST WAY TO SHOW A DIALOG IS CREATINA A FRAGMENT
   alertBuilder.setTitle(title)
       .setMessage(exception)
       .setPositiveButton(
           getString(R.string.try_again),
           DialogInterface.OnClickListener { dialog, id ->
               viewModel.refreshData()
           })
   alertBuilder.show()
}*/

override fun onDestroyView() {
  super.onDestroyView()
  _binding = null
}
}