package com.vondi.weatherapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vondi.weatherapp.ui.screens.ChangeCityScreen
import com.vondi.weatherapp.ui.screens.WeatherScreen
import com.vondi.weatherapp.ui.viewmodels.WeatherViewModel

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: WeatherViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Weather.route
    ) {
        composable(route = Screen.Weather.route) {
            WeatherScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composable(route = Screen.ChangeCity.route) {
            ChangeCityScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}