package com.soethan.citysearch.domain.model

data class CityDomainModel(
    val id: Int,
    val name: String,
    val cityName: String,
    val lat: Double,
    val long: Double
)