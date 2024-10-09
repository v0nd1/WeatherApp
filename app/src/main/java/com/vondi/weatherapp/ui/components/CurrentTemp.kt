package com.vondi.weatherapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vondi.weatherapp.ui.navigation.Screen

private fun getWeatherDescriptionByCode(weatherCode: Int): String {
    return when (weatherCode) {
        0 -> "Солнечно"
        1 -> "Частично облачно"
        2 -> "Переменная облачность"
        3 -> "Пасмурно"
        45 -> "Туман"
        48 -> "Морозный туман"
        51 -> "Слабая морось"
        53 -> "Умеренная морось"
        55 -> "Сильная морось"
        56 -> "Слабая ледяная морось"
        57 -> "Сильная ледяная морось"
        61 -> "Слабый дождь"
        63 -> "Умеренный дождь"
        65 -> "Сильный дождь"
        66 -> "Слабый ледяной дождь"
        67 -> "Сильный ледяной дождь"
        71 -> "Слабый снегопад"
        73 -> "Умеренный снегопад"
        75 -> "Сильный снегопад"
        77 -> "Снежные зерна"
        80 -> "Слабый дождь с ливнями"
        81 -> "Умеренный дождь с ливнями"
        82 -> "Сильный дождь с ливнями"
        85 -> "Слабый снег с ливнями"
        86 -> "Сильный снег с ливнями"
        95 -> "Слабая гроза"
        96 -> "Слабая гроза с градом"
        99 -> "Сильная Гроза с градом"
        else -> "Неизвестная погода"
    }
}


@Composable
fun CurrentTemp(
    temp: Double,
    city: String,
    current: Int,
    navController: NavController
) {
    val weatherDescription = getWeatherDescriptionByCode(current)

    Column(
        modifier = Modifier.fillMaxWidth().clickable { navController.navigate(Screen.ChangeCity.route) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = city,
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "${temp.toInt()}°",
            fontSize = 130.sp,
            color = Color.White,
            fontWeight = FontWeight.Light
        )
        Text(
            text = weatherDescription,
            fontSize = 22.sp,
            color = Color.White
        )
    }
}