package com.hakanninc.wordhive.state

import com.hakanninc.wordhive.data.remote.dto.tdk.TdkDto

data class WordsState(
    val isLoading: Boolean = false,
    val mean: TdkDto? = null,
    val error: String = "",
)