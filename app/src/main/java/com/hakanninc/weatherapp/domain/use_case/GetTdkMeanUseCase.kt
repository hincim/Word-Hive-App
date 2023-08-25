package com.hakanninc.weatherapp.domain.use_case

import com.hakanninc.weatherapp.data.remote.dto.tdk.AnlamlarListe
import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDto
import com.hakanninc.weatherapp.data.remote.dto.tdk.toTdkList
import com.hakanninc.weatherapp.domain.model.TdkWord
import com.hakanninc.weatherapp.domain.repo.TdkRepo
import com.hakanninc.weatherapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject


class GetTdkMeanUseCase @Inject constructor(
    private val repo: TdkRepo
) {
    fun executeGetTdkMean(searchQuery: String) : Flow<Resource<TdkDto>> = flow{
        try {
            emit(Resource.Loading())
            val wordMean = repo.getMean(searchQuery)
            if (wordMean[0].anlamlarListe != null){
                emit(Resource.Success(wordMean))
            }else{
                emit(Resource.Error("No data"))
            }
        }catch (e: IOError){
            emit(Resource.Error(message = "No internet connection"))
        }catch (e: HttpException){
            emit(Resource.Error(message = "Error"))
        }
        catch (e: Exception){
            emit(Resource.Error(message = "No internet connection"))
        }

    }
}