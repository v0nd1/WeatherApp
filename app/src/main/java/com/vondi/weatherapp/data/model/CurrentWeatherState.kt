package com.vondi.weatherapp.data.model

data class CurrentWeatherState(
    var temp: Double,
    var humidity: Int,
    var weatherCode: Int,
    var windSpeed: Double
)