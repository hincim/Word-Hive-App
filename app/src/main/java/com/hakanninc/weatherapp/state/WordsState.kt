package com.hakanninc.weatherapp.state

import com.hakanninc.weatherapp.data.remote.dto.tdk.AnlamlarListe
import com.hakanninc.weatherapp.data.remote.dto.tdk.TdkDto
import com.hakanninc.weatherapp.domain.model.TdkWord

data class WordsState(
    val isLoading: Boolean = false,
    val mean: TdkDto? = null,
    val error: String = "",
)