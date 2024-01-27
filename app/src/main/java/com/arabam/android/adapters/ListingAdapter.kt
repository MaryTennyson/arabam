package com.arabam.android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.databinding.ListingRowBinding
import com.arabam.android.models.listingmodels.Advert
import com.arabam.android.view.ListingPageFragmentDirections
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
        holder.binding.locationView.text =
            "${advertList.get(position).location.cityName}, ${advertList.get(position).location.townName}"
        holder.binding.priceFormattedView.text = advertList.get(position).priceFormatted

        val newpath = advertList.get(position).photo.replace("800x600")
        Glide.with(holder.itemView).load(newpath).fitCenter()
            .into(holder.binding.carImageView)

        holder.itemView.setOnClickListener {
            val action =
                ListingPageFragmentDirections.actionListingPageFragmentToDetailsFragment(
                    advertList.get(position).id
                )
            Navigation.findNavController(it).navigate(action)
        }
    }


    private infix fun String.replace(sizeOfImage: String): String{
        return this.replace("{0}", sizeOfImage)
    }

    fun updateAdvertList(newAdvertList: List<Advert>) {
        advertList.clear()
        advertList.addAll(newAdvertList)
        notifyDataSetChanged()
    }

}