package com.arabam.android.assigment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.databinding.ListingRowBinding

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
         holder.binding.locationView.text   = advertList.get(position).location
         holder.binding.priceFormattedView.text = advertList.get(position).price
         holder.binding.carImageView.setImageResource(advertList.get(position).image)

    //     val intent = Intent(holder.itemView.context,) TODO
         // holder.itemview.content.startActivity(intent)
     }
}