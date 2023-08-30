package com.hakanninc.wordhive.module

import com.hakanninc.wordhive.data.remote.dto.MovieAPI
import com.hakanninc.wordhive.data.remote.dto.TdkAPI
import com.hakanninc.wordhive.data.remote.dto.WeatherAPI
import com.hakanninc.wordhive.data.repo.MovieRepoImpl
import com.hakanninc.wordhive.data.repo.TdkRepoImpl
import com.hakanninc.wordhive.data.repo.WeatherRepoImpl
import com.hakanninc.wordhive.domain.repo.MovieRepo
import com.hakanninc.wordhive.domain.repo.TdkRepo
import com.hakanninc.wordhive.domain.repo.WeatherRepo
import com.hakanninc.wordhive.util.Constants.MOVIE_BASE_URL
import com.hakanninc.wordhive.util.Constants.TDK_BASE_URL
import com.hakanninc.wordhive.util.Constants.WEATHER_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
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

    @Provides
    @Singleton
    fun provideMovieApi() : MovieAPI{

        return Retrofit.Builder()
            .baseUrl(MOVIE_BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectRepo(api: TdkAPI) = TdkRepoImpl(api) as TdkRepo

    @Provides
    @Singleton
    fun provideWeatherRepository(api: WeatherAPI): WeatherRepo{

        return WeatherRepoImpl(api)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieAPI): MovieRepo{

        return MovieRepoImpl(api)
    }
}