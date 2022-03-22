package com.android.app.sampleapplication.di

import android.content.Context
import androidx.room.Room
import com.android.app.sampleapplication.database.AppDatabase
import com.android.app.sampleapplication.database.WeatherDao
import com.android.app.sampleapplication.utils.APIConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun provideRoomDatabase( @ApplicationContext app: Context): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            APIConstants.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideWeatherDao(appDatabase: AppDatabase): WeatherDao {
        return appDatabase.getWeatherDao()
    }
}
