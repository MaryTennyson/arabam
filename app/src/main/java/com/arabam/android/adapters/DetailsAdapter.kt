package com.arabam.android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arabam.android.assigment.databinding.DetailsRowBinding
import com.arabam.android.models.Property


class DetailAdapter(val propertyList: ArrayList<Property>) :
    RecyclerView.Adapter<DetailAdapter.DetailHolder>() {

    class DetailHolder(val binding: DetailsRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapter.DetailHolder {
        val binding = DetailsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailAdapter.DetailHolder, position: Int) {

        holder.binding.detailNameView.text = propertyList.get(position).name
        holder.binding.detailInfoView.text = propertyList.get(position).value

    }

    override fun getItemCount(): Int {
        return propertyList.size
    }

    fun updateAdvertList(newPropertyList: List<Property>) {
        propertyList.clear()
        propertyList.addAll(newPropertyList)
        notifyDataSetChanged()
    }
}