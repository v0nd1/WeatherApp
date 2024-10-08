package com.vondi.weatherapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vondi.weatherapp.R

@Composable
fun WeatherSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val image = painterResource(id = R.drawable.weather)
    Box(
        modifier = modifier
            .fillMaxSize()

    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(10.dp)
        )
        content()
    }

}