package com.android.app.sampleapplication.network

import android.content.Context
import okhttp3.*

class MockRequestInterceptor(private val context: Context) : Interceptor {

    companion object {
        private val JSON_MEDIA_TYPE = MediaType.parse("application/json")
        private const val MOCK = "mock"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val header = request.header(MOCK)

        if (header != null) {
            val filename = request.url().pathSegments().last()
            return Response.Builder()
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .message("")
                .code(200)
                .body(ResponseBody.create(JSON_MEDIA_TYPE, context.assets.open("$filename.json").bufferedReader().use { it.readText() }))
                .build()
        }

        return chain.proceed(request.newBuilder().removeHeader(MOCK).build())
    }
}