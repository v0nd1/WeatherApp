package com.vondi.weatherapp.ui.components.util

import com.vondi.weatherapp.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

data class Day(
    val day: String,
    val icon: Int,
    val min: Double,
    val max: Double
)

fun getWeatherIconByCode(weatherCode: Int): Int {
    return when (weatherCode) {
        0 -> R.drawable.sunny
        1 -> R.drawable.partly_cloudy
        2 -> R.drawable.cloudy
        3 -> R.drawable.rainy
        4 -> R.drawable.storm
        5 -> R.drawable.snowy
        else -> R.drawable.unknown
    }
}

fun createDaysList(
    temperatureMaxList: List<Double>,
    temperatureMinList: List<Double>,
    weatherCodeList: List<Int>
): List<Day> {
    val daysList = mutableListOf<Day>()

    val calendar = Calendar.getInstance()

    for (i in temperatureMaxList.indices) {
        calendar.add(Calendar.DAY_OF_YEAR, i)

        val dayName = when (i) {
            0 -> "Сегодня"
            1 -> "Завтра"
            else -> {
                val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale("ru"))
                val dayOfWeek = dayOfWeekFormat.format(calendar.time)
                dayOfWeek.replaceFirstChar { it.uppercase() }
            }
        }
        val weatherIcon = getWeatherIconByCode(weatherCodeList[i])
        val day = Day(
            day = dayName,
            icon = weatherIcon,
            min = temperatureMinList[i],
            max = temperatureMaxList[i]
        )
        daysList.add(day)

        calendar.add(Calendar.DAY_OF_YEAR, -i)
    }

    return daysList
}