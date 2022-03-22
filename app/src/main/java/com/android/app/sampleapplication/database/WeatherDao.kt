package com.android.app.sampleapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {
    companion object {
        const val WEATHER_TABLE = "weather_table"
        const val PRIMARY_KEY = "id"
        const val NAME = "name"
        const val TEMP_MIN = "temp_min"
        const val TEMP_MAX = "temp_max"
        const val HUMIDITY = "humidity"
    }

    @Insert
    suspend fun insertData(currency: List<WeatherEntity>)

    @Query("SELECT * FROM WEATHER_TABLE WHERE NAME LIKE :city")
    suspend fun searchData(city: String): List<WeatherEntity>?

    @Query("SELECT * FROM WEATHER_TABLE")
    suspend fun getAllData(): List<WeatherEntity>?
}