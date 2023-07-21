package com.arabam.android.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.DetailsActivity
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.ListingRowBinding
import com.arabam.android.models.listingmodels.Advert

class ListingAdapter(val advertList: ArrayList<Advert>):
     RecyclerView.Adapter<ListingAdapter.AdvertHolder>() {
     class AdvertHolder( val binding: ListingRowBinding ): RecyclerView.ViewHolder(binding.root){

     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertHolder {
         val binding = ListingRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AdvertHolder(binding)
     }

     override fun getItemCount(): Int {
          return advertList.size
     }

     override fun onBindViewHolder(holder: AdvertHolder, position: Int) {
         holder.binding.titleView.text= advertList.get(position).title
         holder.binding.locationView.text   = advertList.get(position).location.cityName
         holder.binding.priceFormattedView.text = advertList.get(position).priceFormatted
       //  holder.binding.carImageView.setImageResource(advertList.get(position).image) TODO
         holder.binding.carImageView.setImageResource(R.drawable.sadcar)
         holder.itemView.setOnClickListener {
             val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
             holder.itemView.context.startActivity(intent)
         }

     }
}