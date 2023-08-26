package com.hakanninc.weatherapp.state

import com.hakanninc.weatherapp.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movies: MovieDetail? = null,
    val error: String = "",
)