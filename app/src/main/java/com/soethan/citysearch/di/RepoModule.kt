package com.soethan.citysearch.di

import com.soethan.citysearch.domain.CitySearchRepo
import com.soethan.citysearch.data.CitySearchRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindCitySearchRepo(citySearchRepoImpl: CitySearchRepoImpl): CitySearchRepo


}