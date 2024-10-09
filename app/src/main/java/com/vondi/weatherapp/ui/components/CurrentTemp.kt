package com.vondi.weatherapp.ui.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private fun getWeatherDescriptionByCode(weatherCode: Int): String {
    return when (weatherCode) {
        0 -> "Солнечно"
        1 -> "Переменная облачность"
        2 -> "Пасмурно"
        3 -> "Дождь"
        4 -> "Гроза"
        5 -> "Снег"
        else -> "Неизвестная погода"
    }
}

@Composable
fun CurrentTemp(
    temp: Double,
    city: String,
    current: Int
) {
    val weatherDescription = getWeatherDescriptionByCode(current)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = city,
            fontSize = 22.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${temp.toInt()}°",
            fontSize = 130.sp,
            color = Color.White,
            fontWeight = FontWeight.Light
        )
        Text(
            text = weatherDescription,
            fontSize = 26.sp,
            color = Color.White
        )
    }
}