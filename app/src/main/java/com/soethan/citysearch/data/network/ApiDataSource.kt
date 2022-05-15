package com.soethan.citysearch.data.network

import com.soethan.citysearch.data.network.model.CityResponse


interface ApiDataSource {

    suspend fun loadAllCities() :List<CityResponse>
}