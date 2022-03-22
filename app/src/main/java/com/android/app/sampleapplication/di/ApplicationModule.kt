package com.android.app.sampleapplication.di

import com.android.app.sampleapplication.database.WeatherDao
import com.android.app.sampleapplication.network.APIInterface
import com.android.app.sampleapplication.network.NetworkRepository
import com.android.app.sampleapplication.network.mappers.ResponseToDbMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideDataRepository(apiInterface: APIInterface, weatherDao: WeatherDao, responseToDbMapper: ResponseToDbMapper): NetworkRepository =
        NetworkRepository(apiInterface,weatherDao,responseToDbMapper)


}