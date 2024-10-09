package com.vondi.weatherapp.data.remote

import com.vondi.weatherapp.data.responses.WeatherResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String,
        @Query("daily") daily: String,
        @Query("wind_speed_unit") windSpeedUnit: String = "ms",
        @Query("timezone") timezone: String = "GMT"
    ): WeatherResponse
}
