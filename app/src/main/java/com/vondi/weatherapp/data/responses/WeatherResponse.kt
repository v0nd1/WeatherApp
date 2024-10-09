package com.vondi.weatherapp.data.responses


data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    val generationtime_ms: Double,
    val utc_offset_seconds: Int,
    val timezone: String,
    val timezone_abbreviation: String,
    val elevation: Double,
    val current_units: CurrentUnits,
    val current: CurrentWeather,
    val daily_units: DailyUnits,
    val daily: DailyWeather
)

data class CurrentUnits(
    val time: String,
    val interval: String,
    val temperature_2m: String,
    val relative_humidity_2m: String,
    val weather_code: String,
    val wind_speed_10m: String
)

data class CurrentWeather(
    val time: String,
    val interval: Int,
    val temperature_2m: Double,
    val relative_humidity_2m: Int,
    val weather_code: Int,
    val wind_speed_10m: Double
)

data class DailyUnits(
    val time: String,
    val weather_code: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String
)


data class DailyWeather(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>
)
