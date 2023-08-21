package com.arabam.android.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.DetailsActivity
import com.arabam.android.assigment.R
import com.arabam.android.assigment.databinding.ListingRowBinding
import com.arabam.android.models.listingmodels.Advert
import com.squareup.picasso.Picasso

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
         holder.binding.locationView.text   = "${advertList.get(position).location.cityName}, ${advertList.get(position).location.townName}"
         holder.binding.priceFormattedView.text = advertList.get(position).priceFormatted
       //  holder.binding.carImageView.setImageResource(photoList.get(position)) //TODO İLANLARININ LINK
         val newpath= changeUrl(advertList.get(position).photo)
         Picasso.get().load(newpath).into(holder.binding.carImageView)
        // holder.binding.carImageView.setImageResource(R.drawable.ferrari)
         holder.itemView.setOnClickListener {
             val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
             intent.putExtra("AdvertID",advertList.get(position).id)
             Log.i("test","${advertList.get(position).id}")
             holder.itemView.context.startActivity(intent)
         }

     }
    // Picasso.get().load(postList.get(position).downloadUrl).into(holder.binding.feedImageView)
    private fun changeUrl(imagePath: String):String{
        return  imagePath.replace("{0}","800x600")

    }
}