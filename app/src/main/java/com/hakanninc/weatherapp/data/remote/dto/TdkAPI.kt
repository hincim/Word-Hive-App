package com.hakanninc.weatherapp.data.remote.dto

import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDto
import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDtoItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TdkAPI {


    @GET("gts")
    suspend fun getWordMean(
        @Query("ara") searchQuery : String
    ): TdkDto
}