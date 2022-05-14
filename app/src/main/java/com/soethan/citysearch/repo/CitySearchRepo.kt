package com.soethan.citysearch.repo

import com.soethan.citysearch.domain.model.CityDomainModel

interface CitySearchRepo {
    suspend fun loadAllCities():List<CityDomainModel>
}