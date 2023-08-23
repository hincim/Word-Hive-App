package com.hakanninc.weatherapp.data.remote.dto.tdk_api

data class OrneklerListe(
    val anlam_id: String,
    val kac: String,
    val ornek: String,
    val ornek_id: String,
    val ornek_sira: String,
    val yazar: List<Yazar>,
    val yazar_id: String,
    val yazar_vd: String
)