package com.hakanninc.weatherapp.data.remote.dto

import com.hakanninc.weatherapp.data.remote.dto.movie.MovieDetailDto
import com.hakanninc.weatherapp.data.remote.dto.movie.MoviesDto
import com.hakanninc.weatherapp.util.Constants.MOVIE_API_KEY
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET(".")
    suspend fun getMovies(
        @Query("s") searchString: String,
        @Query("apikey") apiKey: String = MOVIE_API_KEY
    ): MoviesDto

    @GET(".")
    suspend fun getMovieDetail(
        @Query("i") imdbId: String,
        @Query("apikey") apiKey: String = MOVIE_API_KEY
    ): MovieDetailDto
}











