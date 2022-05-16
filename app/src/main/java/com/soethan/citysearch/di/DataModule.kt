package com.soethan.citysearch.di

import com.soethan.citysearch.data.local.LocalDataSource
import com.soethan.citysearch.data.local.LocalDataSourceImpl
import com.soethan.citysearch.data.network.ApiDataSource
import com.soethan.citysearch.data.network.ApiDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource


    @Binds
    @Singleton
    abstract fun bindApiDataSource(apiDataSourceImpl: ApiDataSourceImpl): ApiDataSource
}