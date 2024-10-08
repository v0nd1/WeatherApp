package com.vondi.weatherapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vondi.weatherapp.R

@Composable
fun FiveDaysWeather(

) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 15.dp)
                .background(Color.Transparent)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = stringResource(R.string.calendar),
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Прогноз на 5 дней",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Подробнее",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

            }

            val list = listOf(Day.Now, Day.Next, Day.SupNext, Day.SupNext2, Day.SupNext3)
            Spacer(modifier = Modifier.height(15.dp))

            list.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = it.day,
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.White
                        )
                    }

                    Text(
                        text = "${it.min}°/${it.max}°",
                        fontSize = 18.sp,
                        color = Color.White,
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    thickness = 1.dp
                )
                Spacer(modifier = Modifier.height(5.dp))
            }



        }


    }
}


private sealed class Day(
    val day: String,
    val icon: Int,
    val min: Int,
    val max: Int
) {
    data object Now : Day(
        day = "Сегодня",
        icon = R.drawable.rainy,
        min = 6,
        max = 12
    )

    data object Next : Day(
        day = "Завтра",
        icon = R.drawable.sunny,
        min = 4,
        max = 8
    )

    data object SupNext : Day(
        day = "Понедельник",
        icon = R.drawable.sunny_snow,
        min = 6,
        max = 11
    )
    data object SupNext2 : Day(
        day = "Вторник",
        icon = R.drawable.rainy,
        min = 6,
        max = 11
    )
    data object SupNext3 : Day(
        day = "Среда",
        icon = R.drawable.sunny,
        min = 6,
        max = 11
    )
}