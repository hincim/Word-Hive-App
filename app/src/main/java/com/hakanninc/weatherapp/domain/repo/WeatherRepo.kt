package com.hakanninc.weatherapp.domain.repo

import com.hakanninc.weatherapp.data.remote.dto.weather.WeatherDto

interface WeatherRepo {

    suspend fun getWeather(city: String): WeatherDto
}