package com.hakanninc.weatherapp.module

import android.content.Context
import androidx.room.Room
import com.hakanninc.weatherapp.data.remote.dto.WeatherAPI
import com.hakanninc.weatherapp.data.repo.WeatherRepoImpl
import com.hakanninc.weatherapp.data.room.WordsDatabase
import com.hakanninc.weatherapp.domain.repo.WeatherRepo
import com.hakanninc.weatherapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppApi() : WeatherAPI{

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: WeatherAPI): WeatherRepo{

        return WeatherRepoImpl(api)
    }
}