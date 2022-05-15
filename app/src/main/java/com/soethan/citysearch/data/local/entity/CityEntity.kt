package com.soethan.citysearch.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "city_table")
data class CityEntity(
    @PrimaryKey
    val id: Int,
    val countryName: String?,
    val cityName: String?,
    @Embedded val coordEntity: CoordEntity?
)


