package com.soethan.citysearch.di

import com.soethan.citysearch.data.mapper.EntityMapper
import com.soethan.citysearch.mapper.DomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun provideDataMapper() = DomainMapper()

    @Provides
    @Singleton
    fun provideEntityMapper() = EntityMapper()
}


