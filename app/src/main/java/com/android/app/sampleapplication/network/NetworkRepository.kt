package com.android.app.sampleapplication.network

import com.android.app.sampleapplication.network.models.ApiResponseCallback
import com.android.app.sampleapplication.utils.APIConstants.Companion.ERROR_MSG
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api: APIInterface
) {

    suspend fun searchWeather(city: String, apiResponseCallback: ApiResponseCallback) {
        try {
            val response = api.getData(city)
            if (response != null) {

            } else {
                apiResponseCallback.onError(ERROR_MSG)
            }

        } catch (exp: Exception) {
            apiResponseCallback.onError(exp.message ?: ERROR_MSG)
        }
    }
}