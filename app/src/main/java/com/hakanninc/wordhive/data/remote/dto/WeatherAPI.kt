package com.hakanninc.wordhive.data.remote.dto

import com.hakanninc.wordhive.data.remote.dto.weather.WeatherDto
import com.hakanninc.wordhive.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("units") units: String = "metric")
    : WeatherDto
}