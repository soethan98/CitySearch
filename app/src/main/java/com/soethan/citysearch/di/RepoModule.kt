package com.soethan.citysearch.di

import com.soethan.citysearch.network.ApiDataSource
import com.soethan.citysearch.network.ApiDataSourceImpl
import com.soethan.citysearch.repo.CitySearchRepo
import com.soethan.citysearch.repo.CitySearchRepoImpl
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

    @Binds
    @Singleton
    abstract fun bindApiDataSource(apiDataSourceImpl: ApiDataSourceImpl): ApiDataSource
}