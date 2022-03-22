package com.android.app.sampleapplication.network

import com.android.app.sampleapplication.database.WeatherDao
import com.android.app.sampleapplication.network.mappers.ResponseToDbMapper
import com.android.app.sampleapplication.network.models.ApiResponseCallback
import com.android.app.sampleapplication.utils.APIConstants.Companion.EMPTY_RESULT
import com.android.app.sampleapplication.utils.APIConstants.Companion.ERROR_MSG
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api: APIInterface,
    private val weatherDao: WeatherDao,
    private val responseToDbMapper: ResponseToDbMapper
) {

    suspend fun searchWeather(city: String, apiResponseCallback: ApiResponseCallback) {
        try {
            val result = weatherDao.searchData(city)
            if (result != null && result.isNotEmpty()) {
                apiResponseCallback.onSuccess(Result.Success(result))
            } else {
                apiResponseCallback.onError(Result.Error(EMPTY_RESULT))
            }

        } catch (exp: Exception) {
            apiResponseCallback.onError(Result.Error(exp.message ?: ERROR_MSG))
        }
    }


    suspend fun loadData(apiResponseCallback: ApiResponseCallback) {
        try {
            var list = weatherDao.getAllData()
            if (list != null && list.isNotEmpty()) {
                apiResponseCallback.onSuccess(Result.Success(list))
                return
            }
            val response = api.getData()
            if (response != null && response.isNotEmpty()) {
                weatherDao.insertData(responseToDbMapper.transform(response))
                list = weatherDao.getAllData()
                if (list != null && list.isNotEmpty()) {
                    apiResponseCallback.onSuccess(Result.Success(list))
                } else {
                    apiResponseCallback.onError(Result.Error(ERROR_MSG))
                }
            } else {
                apiResponseCallback.onError(Result.Error(ERROR_MSG))
            }
        } catch (exp: Exception) {
            apiResponseCallback.onError(Result.Error(exp.message ?: ERROR_MSG))
        }
    }
}