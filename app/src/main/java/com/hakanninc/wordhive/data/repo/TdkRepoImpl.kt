package com.hakanninc.wordhive.data.repo

import com.hakanninc.wordhive.data.remote.dto.TdkAPI
import com.hakanninc.wordhive.data.remote.dto.tdk.TdkDto
import com.hakanninc.wordhive.domain.repo.TdkRepo
import javax.inject.Inject

class TdkRepoImpl @Inject constructor(
    private val api: TdkAPI
): TdkRepo {
    override suspend fun getMean(searchQuery: String): TdkDto {
        return api.getWordMean(searchQuery)
    }
}