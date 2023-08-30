package com.hakanninc.wordhive.state

import com.hakanninc.wordhive.domain.model.WeatherModel

data class WeatherState (
    val isLoading: Boolean = false,
    val weather: List<WeatherModel> = emptyList(),
    val error: String = "",
)