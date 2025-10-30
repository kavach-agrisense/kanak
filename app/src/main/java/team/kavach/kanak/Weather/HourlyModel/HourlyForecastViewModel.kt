package team.kavach.kanak.Weather.HourlyModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HourlyForecastViewModel : ViewModel() {

    private val _hourlyForecast = mutableStateOf<HourlyForecast?>(null);
    private val hourlyForecast = _hourlyForecast;
}