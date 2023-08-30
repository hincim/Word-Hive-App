package com.hakanninc.wordhive.data.remote.dto

import com.hakanninc.wordhive.data.remote.dto.tdk.TdkDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TdkAPI {


    @GET("gts")
    suspend fun getWordMean(
        @Query("ara") searchQuery : String
    ): TdkDto
}