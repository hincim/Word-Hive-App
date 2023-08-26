package com.hakanninc.weatherapp.state

import com.hakanninc.weatherapp.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val search: String = "harry potter"
)