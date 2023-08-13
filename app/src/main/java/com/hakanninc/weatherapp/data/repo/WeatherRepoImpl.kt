package com.hakanninc.weatherapp.data.repo

import com.hakanninc.weatherapp.data.remote.dto.WeatherAPI
import com.hakanninc.weatherapp.data.remote.dto.weather.WeatherDto
import com.hakanninc.weatherapp.domain.repo.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val api: WeatherAPI
) : WeatherRepo {

    override suspend fun getWeather(city: String): WeatherDto {
        return api.getWeather(city)
    }
}