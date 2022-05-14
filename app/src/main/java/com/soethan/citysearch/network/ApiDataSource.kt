package com.soethan.citysearch.network

import com.soethan.citysearch.network.model.CityResponse

interface ApiDataSource {

    suspend fun loadAllCities() :List<CityResponse>
}