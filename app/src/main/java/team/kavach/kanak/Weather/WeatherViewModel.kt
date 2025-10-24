package team.kavach.kanak.Weather

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import team.kavach.kanak.BuildConfig
import team.kavach.kanak.RetrofitInstance

class WeatherViewModel : ViewModel() {

    private val _weatherInfo = mutableStateOf<WeatherInfo?>(null)
    val weatherInfo : State<WeatherInfo?> = _weatherInfo;
    val loading = mutableStateOf(true);
    fun fetchWeatherInfo() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.getApiService().getWeatherInfo(
                    BuildConfig.WEATHER_API_KEY,
                    "Narela,%20Delhi"
                )

                _weatherInfo.value = response;
                loading.value = false;
                delay(30 * 60 * 1000L)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("NO WEATHER INFO", "${e.message}")
                delay(60 * 1000L)
            }

            /*while (isActive) {
                try {
                    val response = RetrofitInstance.getApiService().getWeatherInfo(
                        BuildConfig.WEATHER_API_KEY,
                        "Narela,%20Delhi"
                    )
                    _weatherInfo.value = response;
                    loading
                } catch (e : Exception) {

                }
            }*/
        }
    }
}