package com.soethan.citysearch.data.local.entity

import androidx.room.Entity

@Entity(tableName = "coord_table")
data class CoordEntity(
    val lat: Double?,
    val lon: Double?
)