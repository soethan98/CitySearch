package com.soethan.citysearch.data.network

import com.soethan.citysearch.data.network.model.CityResponse
import retrofit2.http.GET

interface CityService {

    @GET("cities")
    suspend fun loadAllCities() :List<CityResponse>
}