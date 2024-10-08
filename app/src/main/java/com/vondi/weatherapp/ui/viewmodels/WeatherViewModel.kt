package com.vondi.weatherapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vondi.weatherapp.data.remote.WeatherApi
import com.vondi.weatherapp.data.requests.WeatherRequest
import com.vondi.weatherapp.data.responses.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherApi: WeatherApi
) : ViewModel() {

    private val _weatherData = MutableStateFlow<WeatherResponse?>(null)
    val weatherData: StateFlow<WeatherResponse?> = _weatherData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(
                    latitude = lat,
                    longitude = lon,
                    current = "temperature_2m,relative_humidity_2m",
                    daily = "temperature_2m_max,temperature_2m_min,apparent_temperature_max,apparent_temperature_min,sunrise,sunset"
                )
                _weatherData.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Ошибка сети: ${e.message}"
            }
        }
    }
}
