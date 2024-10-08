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

    WeatherSurface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp)
                .padding(horizontal = 10.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(R.string.refresh),
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                )

            }
            Spacer(modifier = Modifier.height(80.dp))
            CurrentTemp(temp = 10, city = "Москва", current = "Облачно")
            Spacer(modifier = Modifier.height(40.dp))
            FiveDaysWeather()



//            if (weatherData != null) {
//                // Текущая температура
//                Text(text = "Текущая температура (°C): ${weatherData?.current?.temperature_2m}")
//
//                // Относительная влажность
//                Text(text = "Относительная влажность (%): ${weatherData?.current?.relative_humidity_2m}")
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Дневные данные
//                Text(text = "Дневные максимумы и минимумы:")
//                weatherData?.daily?.time?.forEachIndexed { index, time ->
//                    Text(text = "$time - Макс: ${weatherData!!.daily.temperature_2m_max[index]}°C, Мин: ${weatherData!!.daily.temperature_2m_min[index]}°C")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Ощущаемая температура
//                Text(text = "Ощущаемая температура (макс и мин):")
//                weatherData?.daily?.apparent_temperature_max?.forEachIndexed { index, apparentMax ->
//                    Text(text = "Макс: $apparentMax°C (день ${weatherData!!.daily.time[index]})")
//                }
//                weatherData?.daily?.apparent_temperature_min?.forEachIndexed { index, apparentMin ->
//                    Text(text = "Мин: $apparentMin°C (день ${weatherData!!.daily.time[index]})")
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                // Время восхода и заката
//                Text(text = "Время восхода и заката:")
//                weatherData?.daily?.sunrise?.forEachIndexed { index, sunriseTime ->
//                    Text(text = "Восход: $sunriseTime (день ${weatherData!!.daily.time[index]})")
//                }
//                weatherData?.daily?.sunset?.forEachIndexed { index, sunsetTime ->
//                    Text(text = "Закат: $sunsetTime (день ${weatherData!!.daily.time[index]})")
//                }
//            } else {
//                errorMessage?.let { message ->
//                    Text(text = "Ошибка: $message")
//                }
//            }
        }
    }



}