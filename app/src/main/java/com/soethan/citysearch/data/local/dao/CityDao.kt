package com.soethan.citysearch.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.soethan.citysearch.data.local.entity.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCities(cityList: List<CityEntity>)


    @Query("SELECT * FROM city_table LIMIT :limit OFFSET :offset")
    suspend fun loadCities(limit: Int, offset: Int): List<CityEntity>


    @Query("SELECT * FROM city_table WHERE cityName LIKE :keyword|| '%' LIMIT :limit OFFSET :offset")
    suspend fun searchCities(limit: Int, offset: Int, keyword: String): List<CityEntity>


}