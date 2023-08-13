package com.hakanninc.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakanninc.weatherapp.domain.use_case.GetWeatherInfoUseCase
import com.hakanninc.weatherapp.domain.use_case.GetWeatherUseCase
import com.hakanninc.weatherapp.state.WeatherDetailState
import com.hakanninc.weatherapp.state.WeatherState
import com.hakanninc.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase
) : ViewModel() {

    private val _state = MutableLiveData<WeatherState>()
    val state: LiveData<WeatherState>
        get() = _state

    private val _stateDetail = MutableLiveData<WeatherDetailState>()
    val stateDetail: LiveData<WeatherDetailState>
        get() = _stateDetail

    init {
        getWeatherInfo("Konya")
    }

    private var job: Job? = null

    private fun getWeather(city: String){

        job?.cancel()

        job = getWeatherUseCase.executeGetWeather(city).onEach{

            when(it){
                is Resource.Success ->{
                    _state.value = WeatherState(weather = it.data?: emptyList())
                }
                is Resource.Loading ->{
                    _state.value = _state.value?.copy(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = WeatherState(error = "Error")
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getWeatherInfo(city: String){

        job?.cancel()

        job = getWeatherInfoUseCase.executeWeatherDetails(city).onEach{

            when(it){
                is Resource.Success ->{
                    _stateDetail.value = WeatherDetailState(weather = it.data)
                }
                is Resource.Loading ->{
                    _stateDetail.value = _stateDetail.value?.copy(isLoading = true)
                }
                is Resource.Error ->{
                    _stateDetail.value = WeatherDetailState(error = "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

}














