package com.hakanninc.weatherapp.state

import com.hakanninc.weatherapp.data.remote.dto.weather.WeatherDto

data class WeatherDetailState(
    val isLoading: Boolean = false,
    val weather: WeatherDto? = null,
    val error: String = "",
)