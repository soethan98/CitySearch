package com.soethan.citysearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soethan.citysearch.databinding.ItemCityBinding
import com.soethan.citysearch.presentation.adapter.callbacks.UiCityModelDiffCallback
import com.soethan.citysearch.presentation.model.UiCityModel

class CityListAdapter : ListAdapter<UiCityModel,CityListAdapter.CityItemViewHolder>(UiCityModelDiffCallback()){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
       val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CityItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityItemViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bindItem(it)
        }
    }



    class CityItemViewHolder(private val binding: ItemCityBinding):RecyclerView.ViewHolder(binding.root){
        fun bindItem(item:UiCityModel){
            binding.apply {
                tvCityTitle.text = item.title
            }
        }
    }
}