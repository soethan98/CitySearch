package com.soethan.citysearch.presentation.adapter.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.soethan.citysearch.domain.model.City

class UiCityModelDiffCallback : DiffUtil.ItemCallback<City>() {
    override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
        return oldItem.title == newItem.title
    }
}