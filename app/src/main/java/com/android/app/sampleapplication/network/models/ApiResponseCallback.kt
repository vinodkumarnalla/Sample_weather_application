package com.android.app.sampleapplication.network.models

import com.android.app.sampleapplication.network.Result

interface ApiResponseCallback {
    fun onSuccess(responseData: Result)
    fun onError(message:Result)
}