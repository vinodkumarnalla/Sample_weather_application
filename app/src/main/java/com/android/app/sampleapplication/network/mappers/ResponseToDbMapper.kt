package com.android.app.sampleapplication.network.mappers

import com.android.app.sampleapplication.database.WeatherEntity
import com.android.app.sampleapplication.network.models.ResponseData
import javax.inject.Inject

class ResponseToDbMapper @Inject constructor() :
    BaseMapper<List<ResponseData>, List<WeatherEntity>> {
    override fun transform(data: List<ResponseData>): List<WeatherEntity> {
        val list = arrayListOf<WeatherEntity>()
        data.forEach { item ->
            val entity = WeatherEntity(
                name = item.city.name,
                temp_max = item.main.temp_max,
                temp_min = item.main.temp_min,
                humidity = item.main.humidity
            )
            list.add(entity)
        }
        return list
    }
}