package com.vondi.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.vondi.weatherapp.ui.navigation.NavGraph
import com.vondi.weatherapp.ui.screens.WeatherScreen

import com.vondi.weatherapp.ui.theme.WeatherAppTheme
import com.vondi.weatherapp.ui.viewmodels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                NavGraph(viewModel = viewModel)
            }
        }
    }
}

