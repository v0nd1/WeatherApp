package com.vondi.weatherapp.data.responses


data class WeatherResponse(
    val current: CurrentWeather,
    val daily: DailyWeather
)

data class CurrentWeather(
    val temperature_2m: Float,
    val relative_humidity_2m: Float
)

data class DailyWeather(
    val time: List<String>,
    val temperature_2m_max: List<Float>,
    val temperature_2m_min: List<Float>,
    val apparent_temperature_max: List<Float>,
    val apparent_temperature_min: List<Float>,
    val sunrise: List<String>,
    val sunset: List<String>
)
