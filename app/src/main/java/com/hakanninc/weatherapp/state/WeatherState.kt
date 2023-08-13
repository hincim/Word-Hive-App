package com.hakanninc.weatherapp.state

import com.hakanninc.weatherapp.domain.model.WeatherModel

data class WeatherState (
    val isLoading: Boolean = false,
    val weather: List<WeatherModel> = emptyList(),
    val error: String = "",
)