package com.hakanninc.wordhive.domain.repo

import com.hakanninc.wordhive.data.remote.dto.movie.MovieDetailDto
import com.hakanninc.wordhive.data.remote.dto.movie.MoviesDto

interface MovieRepo {

    suspend fun getMovies(search: String) : MoviesDto
    suspend fun getMovieDetail(imdbId: String) : MovieDetailDto
}