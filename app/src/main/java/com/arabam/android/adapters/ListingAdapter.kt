package com.arabam.android.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.view.DetailsActivity

import com.arabam.android.assigment.databinding.ListingRowBinding
import com.arabam.android.models.listingmodels.Advert
import com.bumptech.glide.Glide


class ListingAdapter(val advertList: ArrayList<Advert>) :
    RecyclerView.Adapter<ListingAdapter.AdvertHolder>() {
    class AdvertHolder(val binding: ListingRowBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertHolder {
        val binding = ListingRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdvertHolder(binding)
    }

    override fun getItemCount(): Int {
        return advertList.size
    }

    override fun onBindViewHolder(holder: AdvertHolder, position: Int) {
        holder.binding.titleView.text = advertList.get(position).title
        holder.binding.locationView.text ="${advertList.get(position).location.cityName}, ${advertList.get(position).location.townName}"
        holder.binding.priceFormattedView.text = advertList.get(position).priceFormatted

        val newpath = changeUrl(advertList.get(position).photo)

        Glide.with(holder.itemView).load(newpath).fitCenter()
            .into(holder.binding.carImageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("AdvertID", advertList.get(position).id)
            Log.i("test", "${advertList.get(position).id}")
            holder.itemView.context.startActivity(intent)
        }

    }
    private fun changeUrl(imagePath: String): String {
        return imagePath.replace("{0}", "800x600")
    }

    fun updateAdvertList(newAdvertList: List<Advert>){
        advertList.clear()
        advertList.addAll(newAdvertList)
        notifyDataSetChanged()
    }
}