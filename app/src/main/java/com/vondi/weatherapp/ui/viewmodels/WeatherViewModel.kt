package com.vondi.weatherapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vondi.weatherapp.data.model.CurrentWeatherState
import com.vondi.weatherapp.data.model.PlaceState
import com.vondi.weatherapp.data.remote.WeatherApi
import com.vondi.weatherapp.data.repository.WeatherRepo
import com.vondi.weatherapp.data.model.responses.CurrentWeather
import com.vondi.weatherapp.data.model.responses.DailyWeather
import com.vondi.weatherapp.data.model.responses.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherResponse?>(null)
    private val _placeState = MutableStateFlow(PlaceState())
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _currentWeatherState = MutableStateFlow<CurrentWeatherState?>(null)


    val weatherState: StateFlow<WeatherResponse?> = _weatherState
    val placeState: StateFlow<PlaceState> = _placeState
    val errorMessage: StateFlow<String?> = _errorMessage

    init {
        viewModelScope.launch {
            val response = weatherRepo.getWeather(
                latitude = _placeState.value.lat,
                longitude = _placeState.value.lon,
                current = "temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m",
                daily = "weather_code,temperature_2m_max,temperature_2m_min"
            )
            _weatherState.value = response
        }
    }

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            val response = weatherRepo.getWeather(
                latitude = _placeState.value.lat,
                longitude = _placeState.value.lon,
                current = "temperature_2m,relative_humidity_2m,weather_code,wind_speed_10m",
                daily = "weather_code,temperature_2m_max,temperature_2m_min"
            )
            _weatherState.value = response
        }
    }

}
