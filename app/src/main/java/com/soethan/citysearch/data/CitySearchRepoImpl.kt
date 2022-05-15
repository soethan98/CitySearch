package com.soethan.citysearch.data


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.soethan.citysearch.data.local.CityPagingSource
import com.soethan.citysearch.data.local.LocalDataSource
import com.soethan.citysearch.data.mapper.EntityMapper
import com.soethan.citysearch.data.network.ApiDataSource
import com.soethan.citysearch.domain.CitySearchRepo
import com.soethan.citysearch.domain.model.City
import com.soethan.citysearch.mapper.DomainMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitySearchRepoImpl @Inject constructor(
    private val apiDataSource: ApiDataSource,
    private val localDataSource: LocalDataSource,
    private val entityMapper: EntityMapper,
    private val domainMapper: DomainMapper,
) : CitySearchRepo {

    override suspend fun loadAllCities(): Flow<PagingData<City>> {


        return withContext(Dispatchers.IO){
            val response = apiDataSource.loadAllCities()
            localDataSource.saveAllCities(entityMapper.transform(response))
             Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20
                ),

            ){
                 CityPagingSource(localDataSource = localDataSource,
                     domainModel = domainMapper
                 )
            }.flow
        }

    }

    override suspend fun searchCities(keyword: String): Flow<PagingData<City>> {
        return withContext(Dispatchers.IO){
            Pager(
                PagingConfig(
                    pageSize = 20,
                    enablePlaceholders = false,
                    initialLoadSize = 20
                ),

                ){
                CityPagingSource(localDataSource = localDataSource,
                    domainModel = domainMapper,
                    query = keyword
                )
            }.flow
        }
    }
}