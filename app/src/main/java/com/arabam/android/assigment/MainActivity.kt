package com.arabam.android.assigment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.arabam.android.assigment.databinding.ActivityMainBinding



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
            advertList.add(Advert("SAHİBİNDEN MİS GİBİ BMW", "10.000TL","Almanya", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN KIPKIRMIZI BIR FERRARİ", "15.000TL","Berlin", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN MİS GİBİ BMW", "10.000TL","Almanya", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN KIPKIRMIZI BIR FERRARİ", "15.000TL","Berlin", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN MİS GİBİ BMW", "10.000TL","Almanya", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN KIPKIRMIZI BIR FERRARİ", "15.000TL","Berlin", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN MİS GİBİ BMW", "10.000TL","Almanya", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN KIPKIRMIZI BIR FERRARİ", "15.000TL","Berlin", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN MİS GİBİ BMW", "10.000TL","Almanya", R.drawable.sadcar))
            advertList.add(Advert("SAHİBİNDEN KIPKIRMIZI BIR FERRARİ", "15.000TL","Berlin", R.drawable.sadcar))

            advertAdapter.notifyDataSetChanged()

        }catch (e:Exception){
            print(e)

        }
    }

}