package com.android.app.sampleapplication.network.models


data class Main (

	val temp : Double,
	val temp_min : Double,
	val temp_max : Double,
	val pressure : Double,
	val sea_level : Double,
	val grnd_level : Double,
	val humidity : Int
)