package com.vondi.weatherapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vondi.weatherapp.R
import com.vondi.weatherapp.ui.components.CurrentTemp
import com.vondi.weatherapp.ui.components.DaysWeather
import com.vondi.weatherapp.ui.components.RefreshIcon
import com.vondi.weatherapp.ui.components.WeatherSurface
import com.vondi.weatherapp.ui.components.WetSpeedCards
import com.vondi.weatherapp.ui.components.util.createDaysList
import com.vondi.weatherapp.ui.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    val weatherState by viewModel.weatherState.collectAsState()
    val placeState by viewModel.placeState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    weatherState?.let { state ->
        val daysList = createDaysList(
            temperatureMaxList = state.daily.temperature2mMax,
            temperatureMinList = state.daily.temperature2mMin,
            weatherCodeList = state.daily.weatherCode
        )

        WeatherSurface {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp, start = 10.dp, end = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RefreshIcon {
                        viewModel.fetchWeather(
                            placeState.lat,
                            placeState.lon
                        )
                    }
                }
                Spacer(modifier = Modifier.height(80.dp))
                CurrentTemp(
                    temp = state.current?.temperature2m ?: 0.0,
                    city = "Москва",
                    current = state.current?.weatherCode ?: 0
                )
                Spacer(modifier = Modifier.height(40.dp))
                WetSpeedCards(
                    wet = state.current?.relativeHumidity2m ?: 0,
                    speed = state.current?.windSpeed10m ?: 0.0
                )
                Spacer(modifier = Modifier.height(40.dp))
                DaysWeather(
                    listDays = daysList
                )
            }
        }
    } ?: run {
        errorMessage?.let { message ->
            Text(text = "Ошибка: $message")
        }
    }
}