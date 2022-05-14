package com.soethan.citysearch.network

import com.soethan.citysearch.network.model.CityResponse
import retrofit2.http.GET

interface CityService {

    @GET("cities")
    suspend fun loadAllCities() :List<CityResponse>
}