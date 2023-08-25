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


    private val _state = MutableLiveData<WeatherDetailState>()
    val state: LiveData<WeatherDetailState>
        get() = _state

    init {
            getWeatherInfo("Konya")
    }

    private var job: Job? = null

    fun getWeatherInfo(city: String){

        job?.cancel()

        job = getWeatherInfoUseCase.executeWeatherDetails(city).onEach{

            when(it){
                is Resource.Success ->{
                    _state.value = WeatherDetailState(weather = it.data)
                }
                is Resource.Loading ->{
                    _state.value = _state.value?.copy(isLoading = true)
                }
                is Resource.Error ->{
                    if (it.message == "No internet connection"){
                        _state.value = WeatherDetailState(error = "No internet connection")
                    }else{
                        _state.value = WeatherDetailState(error = "Error")
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

}















