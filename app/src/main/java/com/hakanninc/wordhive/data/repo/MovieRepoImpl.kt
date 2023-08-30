package com.hakanninc.wordhive.data.repo

import com.hakanninc.wordhive.data.remote.dto.movie.MovieDetailDto
import com.hakanninc.wordhive.data.remote.dto.MovieAPI
import com.hakanninc.wordhive.data.remote.dto.movie.MoviesDto
import com.hakanninc.wordhive.domain.repo.MovieRepo
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