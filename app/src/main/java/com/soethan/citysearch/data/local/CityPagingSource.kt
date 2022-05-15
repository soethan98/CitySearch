package com.soethan.citysearch.data.local

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.soethan.citysearch.domain.model.City
import com.soethan.citysearch.mapper.DomainMapper
import kotlinx.coroutines.delay
import javax.inject.Inject

class CityPagingSource  constructor(private val localDataSource: LocalDataSource,
private val domainModel: DomainMapper,
private val query:String? = null):PagingSource<Int,City>() {
    override fun getRefreshKey(state: PagingState<Int, City>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, City> {
        val page = params.key ?: 0
        return try {
           val entities =  when(query){
                null -> localDataSource.getAllCities(params.loadSize, page * params.loadSize)
                else -> localDataSource.searchCities(params.loadSize,page * params.loadSize,keyword = query)
            }
            val result = domainModel.transformTo(entities)

            // simulate page loading
            if (page != 0) delay(1000)

            LoadResult.Page(
                data = result,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (result.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}