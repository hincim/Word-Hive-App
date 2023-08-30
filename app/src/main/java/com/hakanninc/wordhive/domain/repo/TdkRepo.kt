package com.hakanninc.wordhive.domain.repo

import com.hakanninc.wordhive.data.remote.dto.tdk.TdkDto

interface TdkRepo {

    suspend fun getMean(searchQuery: String): TdkDto

}