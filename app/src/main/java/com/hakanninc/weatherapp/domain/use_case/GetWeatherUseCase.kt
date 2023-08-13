package com.hakanninc.weatherapp.domain.use_case

import com.hakanninc.weatherapp.data.remote.dto.weather.toWeatherList
import com.hakanninc.weatherapp.domain.model.WeatherModel
import com.hakanninc.weatherapp.domain.repo.WeatherRepo
import com.hakanninc.weatherapp.util.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repo: WeatherRepo
) {

    fun executeGetWeather(city: String): kotlinx.coroutines.flow.Flow<Resource<List<WeatherModel>>> = flow{

        try {
            emit(Resource.Loading())
            val weather = repo.getWeather(city)
            emit(Resource.Success(weather.toWeatherList()))
        }catch (e: IOError){
            emit(Resource.Error(message = "No internet connection"))
        }catch (e: HttpException){
            emit(Resource.Error(message = "Error"))
        }

    }

}