package com.hakanninc.wordhive.event

sealed class MoviesEvent {

    data class Search(val searchString: String): MoviesEvent()
}