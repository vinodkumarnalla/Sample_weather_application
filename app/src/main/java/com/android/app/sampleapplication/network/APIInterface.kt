package com.android.app.sampleapplication.network

import com.android.app.sampleapplication.network.models.ResponseData
import com.android.app.sampleapplication.utils.APIConstants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {
    @Headers("mock:true")
    @GET(APIConstants.END_POINT)
    suspend fun getData(): List<ResponseData>
}