package com.soethan.citysearch.domain

import androidx.paging.PagingData
import com.soethan.citysearch.domain.model.City
import com.soethan.citysearch.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface CitySearchRepo {
    suspend fun loadAllCities(fetch:Boolean): Flow<PagingData<City>>

    suspend fun searchCities(keyword:String):Flow<PagingData<City>>
}