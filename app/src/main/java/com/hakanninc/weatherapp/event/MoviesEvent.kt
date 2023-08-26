package com.hakanninc.weatherapp.event

sealed class MoviesEvent {

    data class Search(val searchString: String): MoviesEvent()
}