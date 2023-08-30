package com.hakanninc.wordhive.domain.model

data class WeatherDetailModel(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double,
    )