package com.soethan.citysearch.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.soethan.citysearch.mapper.DataMapper
import com.soethan.citysearch.network.ApiDataSourceImpl
import com.soethan.citysearch.network.CityService
import com.soethan.citysearch.utils.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson) : CityService {
        return Retrofit.Builder().baseUrl(Urls.BASE_URL).client(OkHttpClient.Builder().also { client ->
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            client.addInterceptor(logging)
        }.build()).addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(CityService::class.java)
    }




    


}