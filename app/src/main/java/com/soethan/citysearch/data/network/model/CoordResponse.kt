package com.soethan.citysearch.data.network.model

import androidx.room.Entity


@Entity(tableName = "coord_table")
data class CoordResponse(
    val lat: Double?,
    val lon: Double?
)