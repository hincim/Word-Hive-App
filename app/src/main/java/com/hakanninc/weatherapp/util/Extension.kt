package com.hakanninc.weatherapp.util

fun String.capitalizeFirstLetter(): String {
    return if (isNotEmpty()) {
        this[0].toUpperCase() + substring(1)
    } else {
        this
    }
}