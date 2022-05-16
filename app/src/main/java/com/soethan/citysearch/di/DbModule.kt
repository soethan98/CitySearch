package com.soethan.citysearch.di

import android.app.Application
import androidx.room.Room
import com.soethan.citysearch.data.local.main.CityDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    fun provideCityDatabase(application: Application) =
        Room.databaseBuilder(application, CityDatabase::class.java, "city_db")
            .fallbackToDestructiveMigration()
            .build()
}