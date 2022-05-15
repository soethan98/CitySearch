package com.soethan.citysearch.data.mapper

import com.soethan.citysearch.data.local.entity.CityEntity
import com.soethan.citysearch.data.local.entity.CoordEntity
import com.soethan.citysearch.data.network.model.CityResponse
import javax.inject.Inject

class EntityMapper @Inject constructor() {


    fun transform(response:List<CityResponse>) : List<CityEntity> = response.map(this::transformItem)

    private fun transformItem(responseItem:CityResponse):CityEntity{
        with(responseItem){
            return CityEntity(
                id = id,
                cityName = name,
                countryName = country,
                coordEntity = CoordEntity(coord?.lat, coord?.lon)
            )
        }
    }
}