package com.vondi.weatherapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vondi.weatherapp.R

@Composable
fun WetSpeedCards(
    wet: Float,
    speed: Float
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            border = BorderStroke(0.5.dp, Color.White)
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = stringResource(R.string.wet),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$wet%",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Card (
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            border = BorderStroke(0.5.dp, Color.White)
        ) {
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = stringResource(R.string.sw),
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$speed м/с",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}