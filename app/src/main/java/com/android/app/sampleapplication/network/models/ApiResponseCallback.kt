package com.android.app.sampleapplication.network.models

interface ApiResponseCallback {
    fun onSuccess(responseData: ResponseData)
    fun onError(message:String)
}