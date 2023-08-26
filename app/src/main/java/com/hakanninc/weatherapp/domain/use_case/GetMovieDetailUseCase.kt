package com.hakanninc.weatherapp.domain.use_case

import com.hakanninc.weatherapp.data.remote.dto.movie.toMovieDetail
import com.hakanninc.weatherapp.domain.model.MovieDetail
import com.hakanninc.weatherapp.domain.repo.MovieRepo
import com.hakanninc.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import java.lang.Exception
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repo: MovieRepo
) {
    fun executeGetMovieDetails(imdbId: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repo.getMovieDetail(imdbId)
            if (movieDetail.Response == "True"){
                emit(Resource.Success(movieDetail.toMovieDetail()))
            }else{
                emit(Resource.Error("No movie found"))
            }
        }catch (e: IOError){
            emit(Resource.Error(message = "No internet connection"))
        }catch (e: HttpException){
            emit(Resource.Error(message = "Error"))
        }catch (e: Exception){
            emit(Resource.Error(message = "No internet connection"))
        }
    }
}