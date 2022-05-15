package com.soethan.citysearch.data.local

import androidx.paging.PagingSource
import com.soethan.citysearch.data.local.entity.CityEntity
import com.soethan.citysearch.data.network.model.CityResponse
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun saveAllCities(entities: List<CityEntity>)

    suspend fun getAllCities(limit:Int,offset:Int):List<CityEntity>
    suspend fun searchCities(limit:Int,offset:Int,keyword:String):List<CityEntity>
}