package com.vondi.weatherapp.data.model.responses

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("generationtime_ms") val generationtimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerializedName("elevation") val elevation: Double,
    @SerializedName("current_units") val currentUnits: CurrentUnits,
    @SerializedName("current") val current: CurrentWeather?,
    @SerializedName("daily_units") val dailyUnits: DailyUnits,
    @SerializedName("daily") val daily: DailyWeather
)

data class CurrentUnits(
    @SerializedName("time") val time: String,
    @SerializedName("interval") val interval: String,
    @SerializedName("temperature_2m") val temperature2m: String,
    @SerializedName("relative_humidity_2m") val relativeHumidity2m: String,
    @SerializedName("weather_code") val weatherCode: String,
    @SerializedName("wind_speed_10m") val windSpeed10m: String
)

data class CurrentWeather(
    @SerializedName("time") val time: String,
    @SerializedName("interval") val interval: Int,
    @SerializedName("temperature_2m") val temperature2m: Double,
    @SerializedName("relative_humidity_2m") val relativeHumidity2m: Int,
    @SerializedName("weather_code") val weatherCode: Int,
    @SerializedName("wind_speed_10m") val windSpeed10m: Double
)

data class DailyUnits(
    @SerializedName("time") val time: String,
    @SerializedName("weather_code") val weatherCode: String,
    @SerializedName("temperature_2m_max") val temperature2mMax: String,
    @SerializedName("temperature_2m_min") val temperature2mMin: String
)

data class DailyWeather(
    @SerializedName("time") val time: List<String>,
    @SerializedName("weather_code") val weatherCode: List<Int>,
    @SerializedName("temperature_2m_max") val temperature2mMax: List<Double>,
    @SerializedName("temperature_2m_min") val temperature2mMin: List<Double>
)