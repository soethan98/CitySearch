package com.soethan.citysearch.repo


import com.soethan.citysearch.domain.model.CityDomainModel
import com.soethan.citysearch.mapper.DataMapper
import com.soethan.citysearch.network.ApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitySearchRepoImpl @Inject constructor(private val apiDataSource: ApiDataSource,
                                             private val mapper: DataMapper
): CitySearchRepo {

    override suspend fun loadAllCities(): List<CityDomainModel> {
        return withContext(Dispatchers.IO){
            val cities = apiDataSource.loadAllCities()
            mapper.transformTo(cities)
        }
    }
}