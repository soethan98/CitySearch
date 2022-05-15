package com.soethan.citysearch.data.network.model

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName


data class CityResponse(
    @SerializedName("_id")
    val id: Int,
    val coord: CoordResponse?,
    val country: String?,
    val name: String?
)