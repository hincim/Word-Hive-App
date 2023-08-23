package com.hakanninc.weatherapp.data.repo

import com.hakanninc.weatherapp.data.remote.dto.TdkAPI
import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDto
import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDtoItem
import com.hakanninc.weatherapp.domain.repo.TdkRepo
import javax.inject.Inject

class TdkRepoImpl @Inject constructor(
    private val api: TdkAPI
): TdkRepo {
    override suspend fun getMean(searchQuery: String): TdkDto {
        return api.getWordMean(searchQuery)
    }
}