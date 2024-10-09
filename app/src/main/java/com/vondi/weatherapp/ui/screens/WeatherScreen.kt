package com.vondi.weatherapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vondi.weatherapp.R
import com.vondi.weatherapp.ui.components.CurrentTemp
import com.vondi.weatherapp.ui.components.FiveDaysWeather
import com.vondi.weatherapp.ui.components.WeatherSurface
import com.vondi.weatherapp.ui.components.WetSpeedCards
import com.vondi.weatherapp.ui.components.util.createDaysList
import com.vondi.weatherapp.ui.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel
) {
    val weatherData by viewModel.weatherData.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWeather(lat = 52.52, lon = 37.0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Отображение текущей погоды
        Text(
            text = "Текущая погода",
        )

        Text(
            text = "Температура: ${weatherData.current.temperature_2m}°C",
        )

        Text(
            text = "Влажность: ${weatherData.current.relative_humidity_2m}%", // Изменено на relative_humidity_2m
        )

        Text(
            text = "Скорость ветра: ${weatherData.current.wind_speed_10m} м/с", // Изменено на wind_speed_10m
        )

        Text(
            text = "Код погоды: ${weatherData.current.weather_code}",
        )


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Прогноз на несколько дней",
        )

        for (i in weatherData.daily.temperature_2m_max.indices) {
            Text(
                text = "День ${i + 1}: " +
                        "Макс. температура: ${weatherData.daily.temperature_2m_max[i]}°C, " +
                        "Мин. температура: ${weatherData.daily.temperature_2m_min[i]}°C, " +
                        "Влажность: ${weatherData.daily.relative_humidity_2m[i]}%, " + // Обновлено на relative_humidity_2m
                        "Код погоды: ${weatherData.daily.weather_code[i]}",
            )
        }

    }

//    if (weatherData != null) {
//        val daysList = createDaysList(
//            temperatureMaxList = weatherData!!.daily.temperature_2m_max,
//            temperatureMinList = weatherData!!.daily.temperature_2m_min,
//            weatherCodeList = weatherData!!.daily.weathercode,
//        )
//
//        WeatherSurface {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 40.dp)
//                    .padding(horizontal = 10.dp),
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Start,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Refresh,
//                        contentDescription = stringResource(R.string.refresh),
//                        tint = Color.White,
//                        modifier = Modifier
//                            .size(30.dp)
//                    )
//                }
//                Spacer(modifier = Modifier.height(80.dp))
//                CurrentTemp(
//                    temp = weatherData!!.current_weather.temperature,
//                    city = "Москва",
//                    current = weatherData!!.current_weather.weathercode
//                )
//                Spacer(modifier = Modifier.height(40.dp))
//                WetSpeedCards(
//                    wet = weatherData!!.current_weather.relative_humidity,
//                    speed = weatherData!!.current_weather.windspeed
//                )
//                Spacer(modifier = Modifier.height(40.dp))
//                FiveDaysWeather(
//                    listDays = daysList
//                )
//            }
//        }
//    } else {
//        errorMessage?.let { message ->
//            Text(text = "Ошибка: $message")
//        }
//    }
}