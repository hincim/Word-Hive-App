package com.hakanninc.weatherapp.state

import com.hakanninc.weatherapp.domain.model.TdkWord

data class WordsState(
    val isLoading: Boolean = false,
    val mean: TdkWord? = null,
    val error: String = "",
)