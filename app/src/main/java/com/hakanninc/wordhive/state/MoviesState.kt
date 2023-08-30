package com.hakanninc.wordhive.state

import com.hakanninc.wordhive.domain.model.Movie

data class MoviesState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = "",
    val search: String = "harry potter"
)