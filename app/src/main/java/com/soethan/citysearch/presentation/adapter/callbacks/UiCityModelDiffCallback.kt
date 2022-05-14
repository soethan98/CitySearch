package com.soethan.citysearch.presentation.adapter.callbacks

import androidx.recyclerview.widget.DiffUtil
import com.soethan.citysearch.presentation.model.UiCityModel

class UiCityModelDiffCallback : DiffUtil.ItemCallback<UiCityModel>() {
    override fun areContentsTheSame(oldItem: UiCityModel, newItem: UiCityModel): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: UiCityModel, newItem: UiCityModel): Boolean {
        return oldItem.title == newItem.title
    }
}