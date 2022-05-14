package com.soethan.citysearch.network.model

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("_id")
    val id: Int,
    val coord: CoordResponse?,
    val country: String?,
    val name: String?
)