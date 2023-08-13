package com.hakanninc.weatherapp.data.remote.dto.weather

import com.hakanninc.weatherapp.domain.model.WeatherDetailModel
import com.hakanninc.weatherapp.domain.model.WeatherModel
import com.hakanninc.weatherapp.domain.model.WeatherNameModel

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