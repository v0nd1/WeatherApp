package com.vondi.weatherapp.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vondi.weatherapp.ui.components.CurrentTemp
import com.vondi.weatherapp.ui.components.DaysWeather
import com.vondi.weatherapp.ui.components.RefreshIcon
import com.vondi.weatherapp.ui.components.WeatherSurface
import com.vondi.weatherapp.ui.components.WetSpeedCards
import com.vondi.weatherapp.ui.components.util.createDaysList
import com.vondi.weatherapp.ui.viewmodels.WeatherViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel,
    navController: NavController
) {
    val weatherState by viewModel.weatherState.collectAsState()
    val placeState by viewModel.placeState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val isNetworkAvailable by viewModel.isNetworkAvailable.collectAsState()
    val context = LocalContext.current
    WeatherSurface {
        if (!isNetworkAvailable) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Проблема с сетью! Включите интернет.",
                    color = Color.White, fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            weatherState?.let { state ->
                val daysList = createDaysList(
                    temperatureMaxList = state.daily.temperature2mMax,
                    temperatureMinList = state.daily.temperature2mMin,
                    weatherCodeList = state.daily.weatherCode
                )
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
                            viewModel.checkNetworkAvailability()
                            if (!isNetworkAvailable) {
                                Toast.makeText(
                                    context,
                                    "ПРОБЛЕМЫ С СЕТЬЮ. ВКЛЮЧИТЕ ИНТЕРНЕТ!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else viewModel.fetchWeather()

                        }
                    }
                    Spacer(modifier = Modifier.height(80.dp))
                    CurrentTemp(
                        temp = state.current?.temperature2m ?: 0.0,
                        city = placeState.city,
                        current = state.current?.weatherCode ?: 0,
                        navController = navController
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

            } ?: run {
                errorMessage?.let { message ->
                    Text(text = "Ошибка: $message")
                }
            }

        }

    }


}