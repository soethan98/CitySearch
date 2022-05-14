package com.soethan.citysearch.mapper

import com.soethan.citysearch.domain.model.CityDomainModel
import com.soethan.citysearch.network.model.CityResponse
import javax.inject.Inject

class DataMapper @Inject constructor(){

    fun transformTo(cityDatas:List<CityResponse>):List<CityDomainModel> = cityDatas.map(this::transformItemTo)

    private fun transformItemTo(cityData: CityResponse) : CityDomainModel {
        return with(cityData){
            CityDomainModel(
                id = id,
                name = country ?: "",
                cityName= this.name ?: "",
                lat = coord?.lat ?: 0.0,
                long = coord?.lon ?: 0.0
            )
        }

    }
}