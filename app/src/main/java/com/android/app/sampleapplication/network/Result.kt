package com.android.app.sampleapplication.network

import com.android.app.sampleapplication.network.models.ResponseData

sealed class Result {
    data class Success(val data: ResponseData) : Result()
    data class Error(val message: String) : Result()
    object Loading : Result()
}