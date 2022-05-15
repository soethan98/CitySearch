package com.soethan.citysearch.data.network


import com.soethan.citysearch.data.network.model.CityResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiDataSourceImpl @Inject constructor(private val cityService: CityService): ApiDataSource {

    override suspend fun loadAllCities(): List<CityResponse> {
        return cityService.loadAllCities()
    }
}