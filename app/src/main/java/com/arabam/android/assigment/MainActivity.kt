package com.arabam.android.assigment

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity


import androidx.recyclerview.widget.LinearLayoutManager
import com.arabam.android.adapters.ListingAdapter
import com.arabam.android.assigment.databinding.ActivityMainBinding
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.models.Location

import com.arabam.android.models.listingmodels.Property

import com.arabam.android.services.AdvertService



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var advertList: ArrayList<Advert>
    private lateinit var advertAdapter : ListingAdapter
    lateinit var advertService: AdvertService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.toolbar))
        advertList= ArrayList<Advert>()
        advertAdapter= ListingAdapter(advertList)
        binding.includeRecyclerView.recyclerView.layoutManager= LinearLayoutManager(this)
        binding.includeRecyclerView.recyclerView.adapter=advertAdapter
       val propertyList = listOf(Property("bla","bla"), Property("bla","bla"))




     advertList.add(Advert(Category(12121,"Mükemmel Bir Araç"),"deneme","deneme",12121, Location("Burası","deneme"),"deneme","deneme",12121,"121212tl",propertyList,"Deneme"))
        advertList.add(Advert(Category(12121,"deneme"),"deneme","deneme",12121, Location("deneme","deneme"),"deneme","deneme",12121,"121212tl",propertyList,"Deneme"))


        advertAdapter.notifyDataSetChanged()



}


}

