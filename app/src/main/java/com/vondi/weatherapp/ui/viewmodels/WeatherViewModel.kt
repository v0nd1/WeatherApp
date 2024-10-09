package com.vondi.weatherapp.ui.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vondi.weatherapp.data.model.PlaceState
import com.vondi.weatherapp.data.repository.WeatherRepo
import com.vondi.weatherapp.data.model.responses.WeatherResponse
import com.vondi.weatherapp.ui.screens.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepo: WeatherRepo,
    private val context: Context
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherResponse?>(null)
    private val _placeState = MutableStateFlow(PlaceState())
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _isNetworkAvailable = MutableStateFlow(true)

    val weatherState: StateFlow<WeatherResponse?> = _weatherState
    val placeState: StateFlow<PlaceState> = _placeState
    val errorMessage: StateFlow<String?> = _errorMessage
    val isNetworkAvailable: StateFlow<Boolean> = _isNetworkAvailable

    init {
        checkNetworkAvailability()
        fetchWeather()
    }

    @SuppressLint("ServiceCast")
    fun checkNetworkAvailability() {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        _isNetworkAvailable.value = networkInfo != null && networkInfo.isConnected
    }

    fun fetchWeather() {
        if (!_isNetworkAvailable.value) return
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

    fun changePlace(city: City) {
        _placeState.update {
            it.copy(
                lat = city.lat,
                lon = city.lon,
                city = city.name
            )
        }
        fetchWeather()
        Log.d("ChangeCity", "${placeState.value}")

    }

}
