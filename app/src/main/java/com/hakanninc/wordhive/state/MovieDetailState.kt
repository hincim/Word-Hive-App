package com.hakanninc.wordhive.state

import com.hakanninc.wordhive.domain.model.MovieDetail

data class MovieDetailState(
    val isLoading: Boolean = false,
    val movies: MovieDetail? = null,
    val error: String = "",
)