package com.hakanninc.weatherapp.domain.repo

import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDto
import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDtoItem

interface TdkRepo {

    suspend fun getMean(searchQuery: String): TdkDto

}