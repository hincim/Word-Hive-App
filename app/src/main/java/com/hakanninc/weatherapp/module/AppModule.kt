package com.hakanninc.weatherapp.module

import com.hakanninc.weatherapp.data.remote.dto.TdkAPI
import com.hakanninc.weatherapp.data.remote.dto.WeatherAPI
import com.hakanninc.weatherapp.data.repo.TdkRepoImpl
import com.hakanninc.weatherapp.data.repo.WeatherRepoImpl
import com.hakanninc.weatherapp.domain.repo.TdkRepo
import com.hakanninc.weatherapp.domain.repo.WeatherRepo
import com.hakanninc.weatherapp.util.Constants.TDK_BASE_URL
import com.hakanninc.weatherapp.util.Constants.WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi() : WeatherAPI{

        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideTdkApi() : TdkAPI{

        return Retrofit.Builder()
            .baseUrl(TDK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TdkAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectRepo(api: TdkAPI) = TdkRepoImpl(api) as TdkRepo

    @Provides
    @Singleton
    fun provideMovieRepository(api: WeatherAPI): WeatherRepo{

        return WeatherRepoImpl(api)
    }
}