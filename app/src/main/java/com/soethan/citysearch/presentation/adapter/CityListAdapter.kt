package com.soethan.citysearch.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soethan.citysearch.databinding.ItemCityBinding
import com.soethan.citysearch.domain.model.City
import com.soethan.citysearch.presentation.adapter.callbacks.UiCityModelDiffCallback

class CityListAdapter(private val clickListener: OnCityItemClickListener) :
    PagingDataAdapter<City, CityListAdapter.CityItemViewHolder>(UiCityModelDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityItemViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityItemViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: CityItemViewHolder, position: Int) {
        val item = getItem(position)

        item?.let {
            holder.bindItem(it)
        }

    }


    inner class CityItemViewHolder(
        private val binding: ItemCityBinding,
        private var clickListener: OnCityItemClickListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(item: City) {
            binding.apply {
                tvCityTitle.text = item.title

                tvCoord.text = "${item.lat}, ${item.long}"
            }

            itemView.setOnClickListener {
                clickListener.onCityClick(item)
            }
        }
    }


    class OnCityItemClickListener(val clickListener: (cityItem: City) -> Unit) {
        fun onCityClick(cityItem: City) = clickListener(cityItem)
    }
}