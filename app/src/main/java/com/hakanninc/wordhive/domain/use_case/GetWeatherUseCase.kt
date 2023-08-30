package com.hakanninc.wordhive.domain.use_case

import com.hakanninc.wordhive.data.remote.dto.weather.toWeatherList
import com.hakanninc.wordhive.domain.model.WeatherModel
import com.hakanninc.wordhive.domain.repo.WeatherRepo
import com.hakanninc.wordhive.util.Resource
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