package com.arabam.android.assigment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android.assigment.databinding.ActivityMainBinding
import com.arabam.android.models.Advert
import com.arabam.android.services.AdvertService


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var advertList: ArrayList<Advert>
    private lateinit var advertAdapter : ListingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        advertList= ArrayList<Advert>()
        advertAdapter= ListingAdapter(advertList)

        binding.includeRecyclerView.recyclerView.layoutManager= LinearLayoutManager(this)
        binding.includeRecyclerView.recyclerView.adapter=advertAdapter


        try {
            advertList= AdvertService().getAdvertList()
            advertAdapter.notifyDataSetChanged()

        }catch (e:Exception){
            print(e)

        }
    }

}