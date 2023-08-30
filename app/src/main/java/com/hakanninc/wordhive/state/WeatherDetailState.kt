package com.hakanninc.wordhive.state

import com.hakanninc.wordhive.data.remote.dto.weather.WeatherDto

data class WeatherDetailState(
    val isLoading: Boolean = false,
    val weather: WeatherDto? = null,
    val error: String = "",
)