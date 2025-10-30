package team.kavach.kanak.Weather

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import team.kavach.kanak.BuildConfig
import team.kavach.kanak.RetrofitInstance
import team.kavach.kanak.Weather.CurrentModel.WeatherInfo
import team.kavach.kanak.Weather.HourlyModel.HourlyForecast


private val exampleLatitude = 28.85
private val exampleLongitude = 77.099

class WeatherViewModel : ViewModel() {

    private val _weatherInfo = mutableStateOf<WeatherInfo?>(null)
    val weatherInfo : State<WeatherInfo?> = _weatherInfo
    val currentWeatherisLoading = mutableStateOf(true)

    private val _hourlyForecastInfo = mutableStateOf<HourlyForecast?>(null)
    val hourlyForecastInfo : State<HourlyForecast?> = _hourlyForecastInfo
    val hourlyForecastisLoading = mutableStateOf(true)





    fun fetchCurrentWeather() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getApiService()
                    .fetchCurrentWeather(BuildConfig.WEATHER_API_KEY,
                        exampleLatitude,
                        exampleLongitude,
                            "temperature,weatherCondition,precipitation.probability,precipitation.qpf,relativeHumidity,wind"
                )
                _weatherInfo.value = response
                currentWeatherisLoading.value = false
            } catch (e : Exception) {
                Log.e("FAILED FETCHING CURRENT WEATHER", "${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun fetchHourlyForecast() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getApiService()
                    .fetchHourlyForecast(
                        BuildConfig.WEATHER_API_KEY,
                        exampleLatitude,
                        exampleLongitude,
                    )
                _hourlyForecastInfo.value = response
                hourlyForecastisLoading.value = false
            } catch (e : Exception) {
                Log.e("HOURLY FORECAST", "${e.message}")
                e.printStackTrace()
            }
        }
    }



}

@Composable
fun fetchAndReturnWeatherViewModel() : WeatherViewModel {
    val viewModel : WeatherViewModel = viewModel();

    LaunchedEffect(Unit) {
        launch {
            viewModel.fetchCurrentWeather()
        }
        launch {
            viewModel.fetchHourlyForecast()
        }
    }

    return viewModel
}