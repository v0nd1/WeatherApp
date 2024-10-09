package com.vondi.weatherapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vondi.weatherapp.R
import com.vondi.weatherapp.ui.components.SearchCityBar
import com.vondi.weatherapp.ui.components.WeatherSurface
import com.vondi.weatherapp.ui.viewmodels.WeatherViewModel

@Composable
fun ChangeCityScreen(
    viewModel: WeatherViewModel,
    navController: NavController
) {
    val placeState by viewModel.placeState.collectAsState()
    val isNetworkAvailable by viewModel.isNetworkAvailable.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current
    val cities = listOf(
        City("Москва", 55.7558, 37.6173),
        City("Санкт-Петербург", 59.9343, 30.3351),
        City("Новосибирск", 55.0084, 82.9357),
        City("Екатеринбург", 56.8389, 60.6057),
        City("Казань", 55.8304, 49.0661),
        City("Нижний Новгород", 56.2965, 43.9361),
        City("Челябинск", 55.1644, 61.4368),
        City("Самара", 53.1959, 50.1008),
        City("Уфа", 54.7388, 55.9721),
        City("Красноярск", 56.0097, 92.7917),
        City("Воронеж", 51.6615, 39.2003),
        City("Пермь", 58.0105, 56.2294),
        City("Волгоград", 48.7080, 44.5146),
        City("Краснодар", 45.0355, 38.9753),
        City("Саратов", 51.5336, 46.0343),
        City("Тюмень", 57.1530, 65.5343),
        City("Тольятти", 53.5200, 49.3497),
        City("Ижевск", 56.8527, 53.2045),
        City("Барнаул", 53.3481, 83.7798),
        City("Ульяновск", 54.3170, 48.4024),
        City("Иркутск", 52.2869, 104.3050),
        City("Хабаровск", 48.4827, 135.0838),
        City("Ярославль", 57.6261, 39.8845),
        City("Владивосток", 43.1155, 131.8855),
        City("Махачкала", 42.9831, 47.5047),
        City("Оренбург", 51.7875, 55.1018),
        City("Новокузнецк", 53.7596, 87.1210),
        City("Томск", 56.4846, 84.9487),
        City("Кемерово", 55.3547, 86.0878),
        City("Рязань", 54.6292, 39.7359),
        City("Астрахань", 46.3476, 48.0336),
        City("Пенза", 53.1950, 45.0183),
        City("Липецк", 52.6031, 39.5708),
        City("Тула", 54.1920, 37.6156),
        City("Киров", 58.6035, 49.6679),
        City("Чебоксары", 56.1439, 47.2489),
        City("Курск", 51.7304, 36.1926),
        City("Брянск", 53.2436, 34.3646),
        City("Магнитогорск", 53.4072, 58.9791),
        City("Сочи", 43.6028, 39.7342),
        City("Ставрополь", 45.0445, 41.9692),
        City("Улан-Удэ", 51.8345, 107.5848),
        City("Тверь", 56.8587, 35.9176)
    )


    val filteredCities = cities.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    WeatherSurface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, start = 10.dp, end = 10.dp)
        ) {
            SearchCityBar(searchQuery) { newQuery ->
                searchQuery = newQuery
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(filteredCities) { city ->
                    CityItem(
                        city = city,
                        isSelected = city.lat == placeState.lat && city.lon == placeState.lon,
                        onCityClick = {
                            viewModel.checkNetworkAvailability()
                            if (!isNetworkAvailable)
                                Toast.makeText(
                                    context,
                                    "ПРОБЛЕМЫ С СЕТЬЮ. ВКЛЮЧИТЕ ИНТЕРНЕТ!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            else {
                                viewModel.changePlace(city = city)
                                navController.popBackStack()
                            }
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
            }
        }
    }
}

@Composable
fun CityItem(
    city: City,
    isSelected: Boolean,
    onCityClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCityClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = city.name,
            modifier = Modifier.weight(1f),
            color = Color.White,
            fontSize = 18.sp
        )

        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.selected),
                tint = Color.White
            )
        }
    }
}

data class City(
    val name: String,
    val lat: Double,
    val lon: Double
)