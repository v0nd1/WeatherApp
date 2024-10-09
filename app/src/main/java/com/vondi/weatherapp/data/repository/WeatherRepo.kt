package com.vondi.weatherapp.data.repository

import com.vondi.weatherapp.data.remote.WeatherApi
import com.vondi.weatherapp.data.model.responses.CurrentWeather
import com.vondi.weatherapp.data.model.responses.WeatherResponse
import retrofit2.http.Query
import javax.inject.Inject

class WeatherRepo @Inject constructor(
    private val weatherApi: WeatherApi
) {
    suspend fun getWeather(
        latitude: Double,
        longitude: Double,
        current: String,
        daily: String
    ) : WeatherResponse {
        val response = weatherApi.getWeather(
            latitude = latitude,
            longitude = longitude,
            current = current,
            daily = daily
        )

        return response
    }


}