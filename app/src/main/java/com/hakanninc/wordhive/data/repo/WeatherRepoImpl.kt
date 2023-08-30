package com.hakanninc.wordhive.data.repo

import com.hakanninc.wordhive.data.remote.dto.WeatherAPI
import com.hakanninc.wordhive.data.remote.dto.weather.WeatherDto
import com.hakanninc.wordhive.domain.repo.WeatherRepo
import javax.inject.Inject

class WeatherRepoImpl @Inject constructor(
    private val api: WeatherAPI
) : WeatherRepo {

    override suspend fun getWeather(city: String): WeatherDto {
        return api.getWeather(city)
    }
}