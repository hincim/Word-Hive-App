package com.hakanninc.weatherapp.data.repo

import com.hakanninc.weatherapp.data.remote.dto.movie.MovieDetailDto
import com.hakanninc.weatherapp.data.remote.dto.MovieAPI
import com.hakanninc.weatherapp.data.remote.dto.movie.MoviesDto
import com.hakanninc.weatherapp.domain.repo.MovieRepo
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val api: MovieAPI
): MovieRepo {
    override suspend fun getMovies(search: String): MoviesDto {
        return api.getMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return api.getMovieDetail(imdbId)
    }
}