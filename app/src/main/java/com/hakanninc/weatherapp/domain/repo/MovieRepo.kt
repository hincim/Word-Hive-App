package com.hakanninc.weatherapp.domain.repo

import com.hakanninc.weatherapp.data.remote.dto.movie.MovieDetailDto
import com.hakanninc.weatherapp.data.remote.dto.movie.MoviesDto
import io.reactivex.rxjava3.core.Single

interface MovieRepo {

    suspend fun getMovies(search: String) : MoviesDto
    suspend fun getMovieDetail(imdbId: String) : MovieDetailDto
}