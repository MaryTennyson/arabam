package com.arabam.android.adapters


import android.view.LayoutInflater

import android.view.ViewGroup

import com.arabam.android.assigment.databinding.ImageViewerBinding

import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter


class SliderAdapter(val imageUrl: List<String>) :
    SliderViewAdapter<SliderAdapter.SliderViewHolder>() {

    class SliderViewHolder(val binding: ImageViewerBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {
    }


    override fun getCount(): Int {
        return imageUrl.size
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapter.SliderViewHolder {
        val binding =
            ImageViewerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return SliderViewHolder(binding)
    }


    override fun onBindViewHolder(viewHolder: SliderAdapter.SliderViewHolder?, position: Int) {
        if (viewHolder != null) {
           val convertedList= changeUrl(imageUrl)
            Glide.with(viewHolder.itemView).load(convertedList.get(position)).fitCenter()
                .into(viewHolder.binding.imageViewSlider)
        }
    }

}

private fun changeUrl(notconvertedUrl: List<String>):ArrayList<String>{
    val convertedList= ArrayList<String>()
    for (imagepath in notconvertedUrl){
      val newimagepath= imagepath.replace("{0}","800x600")
      convertedList.add(newimagepath)
    }
    return  convertedList

}