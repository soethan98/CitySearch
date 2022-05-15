package com.soethan.citysearch.data.local

import android.util.Log
import androidx.paging.PagingSource
import com.soethan.citysearch.data.local.entity.CityEntity
import com.soethan.citysearch.data.local.main.CityDatabase
import com.soethan.citysearch.data.network.model.CityResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val cityDatabase: CityDatabase) : LocalDataSource {

    override suspend fun saveAllCities(entities: List<CityEntity>) {

        try {
           val result =  cityDatabase.cityDao().saveCities(entities)

        }catch (e:Exception){
            Log.i("LocalDataSource",e.message ?: e.localizedMessage)
        }
    }

    override suspend fun getAllCities(limit:Int,offset:Int): List<CityEntity> {
        return cityDatabase.cityDao().loadCities(limit, offset)
    }

    override suspend fun searchCities(limit: Int, offset: Int, keyword: String): List<CityEntity> {
        return cityDatabase.cityDao().searchCities(limit, offset,keyword = keyword)
    }
}