package com.soethan.citysearch.mapper

import com.soethan.citysearch.data.local.entity.CityEntity
import com.soethan.citysearch.domain.model.City
import javax.inject.Inject

class DomainMapper @Inject constructor() {

    fun transformTo(cityDatas: List<CityEntity>): List<City> = cityDatas.map(this::transformItemTo)

    private fun transformItemTo(cityData: CityEntity): City {
        return with(cityData) {
            City(
                id = id,
                title = "$cityName,$countryName",
                lat = coordEntity?.lat ?: 0.0,
                long = coordEntity?.lon ?: 0.0
            )
        }

    }
}