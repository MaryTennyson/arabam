package com.arabam.android.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.arabam.android.assigment.R
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSlideAdapter
    (val images: ArrayList<Int>) : SliderViewAdapter<ImageSlideAdapter.SlideViewHolder>() {
    var slideList: ArrayList<Int> = images

    class SlideViewHolder(itemView: View) : SliderViewAdapter.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.imageViewSlider)
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): ImageSlideAdapter.SlideViewHolder {
      val inflate: View= LayoutInflater.from(parent!!.context).inflate(R.layout.detailed_main,null)
       return SlideViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: ImageSlideAdapter.SlideViewHolder?, position: Int) {
      if(viewHolder!=null){
          Glide.with(viewHolder.itemView).load(slideList.get(position)).fitCenter().into(viewHolder.imageView)
      }
    }

}



