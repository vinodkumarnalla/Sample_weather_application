package com.android.app.sampleapplication.di

import android.content.Context
import com.android.app.sampleapplication.network.APIInterface
import com.android.app.sampleapplication.network.MockRequestInterceptor
import com.android.app.sampleapplication.utils.APIConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideBaseUrl() = APIConstants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(MockRequestInterceptor(appContext))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun getAPIClient(retrofit: Retrofit): APIInterface {
        return retrofit.create(APIInterface::class.java)
    }
}