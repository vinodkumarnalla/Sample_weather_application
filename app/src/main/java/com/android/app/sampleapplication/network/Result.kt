package com.android.app.sampleapplication.network

import com.android.app.sampleapplication.database.WeatherEntity
import com.android.app.sampleapplication.network.models.ResponseData

sealed class Result {
    data class Success(val data: List<WeatherEntity>) : Result()
    data class Error(val message: String) : Result()
    object Loading : Result()
}