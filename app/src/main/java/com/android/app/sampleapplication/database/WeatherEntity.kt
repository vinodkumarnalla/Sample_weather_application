package com.android.app.sampleapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = WeatherDao.WEATHER_TABLE)
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = WeatherDao.NAME) val name: String,
    @ColumnInfo(name = WeatherDao.TEMP_MIN) val temp_min: Double,
    @ColumnInfo(name = WeatherDao.TEMP_MAX) val temp_max: Double,
    @ColumnInfo(name = WeatherDao.HUMIDITY) val humidity: Int
)