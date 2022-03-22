package com.android.app.sampleapplication.network.mappers

interface BaseMapper<DATA, MODEL> {
    fun transform(data: DATA): MODEL
}