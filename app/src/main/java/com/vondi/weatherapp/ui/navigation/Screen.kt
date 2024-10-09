package com.vondi.weatherapp.ui.navigation

sealed class Screen(
    val route: String
) {

    data object Weather : Screen(
        route = "weather"
    )

    data object ChangeCity : Screen(
        route = "changeCity"
    )

}