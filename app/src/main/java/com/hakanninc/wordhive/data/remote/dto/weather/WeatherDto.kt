package com.hakanninc.wordhive.data.remote.dto.weather

import com.hakanninc.wordhive.domain.model.WeatherModel

data class WeatherDto(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)

fun WeatherDto.toWeatherList() :List<WeatherModel> {
    return weather.map { weather -> WeatherModel(weather.description,weather.icon) }
}
