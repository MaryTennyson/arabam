package com.arabam.android.view

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android.adapters.DetailAdapter
import com.arabam.android.adapters.SliderAdapter
import com.arabam.android.assigment.databinding.FragmentDetailsBinding
import com.arabam.android.enums.DataState
import com.arabam.android.models.detailsmodels.Details
import com.arabam.android.viewmodel.DetailsPageViewModel
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsPageViewModel by viewModels()
    private var detailAdapter = DetailAdapter(arrayListOf())
    private lateinit var sliderView: SliderView
    lateinit var sliderAdapter: SliderAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.detailsRecyclerView.adapter = detailAdapter
        sliderView = binding.imageslider
        binding.descriptions.visibility = View.GONE
        binding.detailsRecyclerView.visibility = View.VISIBLE
        binding.detailslayout.visibility = View.GONE
        arguments?.let {
            val advertID = DetailsFragmentArgs.fromBundle(it).advertID
            if (advertID == 0) {
                println("bir hata meydana geldi")
            } else {
                viewModel.refreshData(advertID)
                observeDetails()
            }
        }
        binding.descriptionText.setOnClickListener { onClickedDescription(view) }
        binding.detailsInformations.setOnClickListener { onClickedInformation(view) }
    }

    fun onClickedInformation(view: View) {
        binding.descriptions.visibility = View.GONE
        binding.detailsRecyclerView.visibility = View.VISIBLE
    }

    fun onClickedDescription(view: View) {
        binding.detailsRecyclerView.visibility = View.GONE
        binding.descriptions.visibility = View.VISIBLE
    }

    private fun observeDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is DataState.onPending -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.detailslayout.visibility = View.GONE
                        }
                        is DataState.onSuccess -> {
                            val detail = it.news as Details
                            detailAdapter.updateAdvertList(detail.properties)
                            binding.titleView.text = detail.title
                            binding.locationView.text =
                                "${detail.location.cityName}, ${detail.location.townName}"
                            binding.priceView.text = detail.priceFormatted
                            binding.userName.text = detail.userInfo.nameSurname
                            binding.userPhoneNumber.text = "+${detail.userInfo.phone}"
                            binding.descriptions.text = Html.fromHtml(detail.text).toString()
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
                            //  showAlertDialog(it.title, it.exception)


                        }
                    }
                }
            }
        }
    }


}