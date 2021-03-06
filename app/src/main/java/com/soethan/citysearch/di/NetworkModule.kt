package com.soethan.citysearch.di

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.soethan.citysearch.data.network.CityService
import com.soethan.citysearch.utils.ConnectivityChecker
import com.soethan.citysearch.utils.Urls
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            client.addInterceptor(logging)
        }.build()).addConverterFactory(GsonConverterFactory.create(gson)).build()
            .create(CityService::class.java)
    }



    @Provides
    @Singleton
    fun provideConnectivityChecker(@ApplicationContext appContext:Context):ConnectivityChecker{
        return ConnectivityChecker(appContext)
    }

    


}