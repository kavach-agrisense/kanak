package team.kavach.kanak.Weather.Forecast

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.kavach.kanak.BuildConfig
import team.kavach.kanak.RetrofitInstance

class ForecastViewModel : ViewModel() {

    private val _forecastInfo = mutableStateOf<ForecastInfo?>(null)
    val forecastInfo: State<ForecastInfo?> = _forecastInfo

    val loading = mutableStateOf(true)

    fun fetchWeatherForecast() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getApiService()
                    .fetchWeatherForecast(BuildConfig.WEATHER_API_KEY, "Narela, Delhi", 3)

                _forecastInfo.value = response
                loading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("FORECAST FETCH FAILED", "${e.message}")
            }
        }
    }
}