package com.hakanninc.weatherapp.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakanninc.weatherapp.domain.use_case.GetMovieUseCase
import com.hakanninc.weatherapp.event.MoviesEvent
import com.hakanninc.weatherapp.state.MoviesState
import com.hakanninc.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase
): ViewModel() {

    private val _state = MutableLiveData<MoviesState>()
    val state: LiveData<MoviesState>
        get() = _state

    private var job: Job? = null


    init {
        getMovies("harry")
    }

    private fun getMovies(search:String){
        job?.cancel()
        job = getMovieUseCase.executeGetMovies(search).onEach {

            when(it){
                is Resource.Success ->{
                    _state.value = MoviesState(movies = it.data ?: emptyList())
                }
                is Resource.Loading ->{
                    _state.value = _state.value?.copy(isLoading = true)
                }
                is Resource.Error ->{
                    _state.value = MoviesState(error = "Error")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: MoviesEvent){
        when(event){
            is MoviesEvent.Search ->{
                getMovies(event.searchString)
            }
        }
    }

}









