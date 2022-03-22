package com.android.app.sampleapplication.network.models

data class ResponseData(

    val city: City,
    val time: Int,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds,
    val weather: List<Weather>
)