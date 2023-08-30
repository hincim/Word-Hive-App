package com.hakanninc.wordhive.domain.repo

import com.hakanninc.wordhive.data.remote.dto.weather.WeatherDto

interface WeatherRepo {

    suspend fun getWeather(city: String): WeatherDto
}