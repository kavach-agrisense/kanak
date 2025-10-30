package team.kavach.kanak.Weather.CurrentModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AcUnit
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material.icons.rounded.Grain
import androidx.compose.material.icons.rounded.Radar
import androidx.compose.material.icons.rounded.SevereCold
import androidx.compose.material.icons.rounded.Terrain
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.Thunderstorm
import androidx.compose.material.icons.rounded.Umbrella
import androidx.compose.material.icons.rounded.WbCloudy
import androidx.compose.material.icons.rounded.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import team.kavach.kanak.R


data class WeatherInfo(
    val precipitation: Precipitation,
    val relativeHumidity: Int,
    val temperature: Temperature,
    val weatherCondition: WeatherCondition,
    val wind: Wind
) {
    fun getWeatherIcon(): Int {
        return when (weatherCondition.type) {
            // Clear Conditions
            "CLEAR" -> R.drawable.sunny
            "MOSTLY_CLEAR" -> R.drawable.sunny

            // Cloudy Conditions
            "PARTLY_CLOUDY" -> R.drawable.cloudy_day
            "MOSTLY_CLOUDY" -> R.drawable.cloud
            "CLOUDY" -> R.drawable.cloud

            // Windy Conditions
            "WINDY" -> R.drawable.windy
            "WIND_AND_RAIN" -> R.drawable.thunderstorm // Good representation for wind and rain

            // Rain Conditions
            "LIGHT_RAIN_SHOWERS", "CHANCE_OF_SHOWERS", "SCATTERED_SHOWERS", "RAIN_SHOWERS", "LIGHT_RAIN" -> R.drawable.thunderstorm
            "HEAVY_RAIN_SHOWERS", "LIGHT_TO_MODERATE_RAIN", "MODERATE_TO_HEAVY_RAIN", "RAIN", "HEAVY_RAIN", "RAIN_PERIODICALLY_HEAVY" -> R.drawable.umbrella

            // Snow Conditions
            "LIGHT_SNOW_SHOWERS", "CHANCE_OF_SNOW_SHOWERS", "SCATTERED_SNOW_SHOWERS", "SNOW_SHOWERS", "LIGHT_SNOW" -> R.drawable.snowing
            "HEAVY_SNOW_SHOWERS", "LIGHT_TO_MODERATE_SNOW", "MODERATE_TO_HEAVY_SNOW", "SNOW", "HEAVY_SNOW", "SNOW_PERIODICALLY_HEAVY" -> R.drawable.snowing_cold
            "SNOWSTORM", "HEAVY_SNOW_STORM", "BLOWING_SNOW" -> R.drawable.snow

            // Mixed Conditions
            "RAIN_AND_SNOW" -> R.drawable.snow // Often represented by a snowflake icon

            // Hail Conditions
            "HAIL", "HAIL_SHOWERS" -> R.drawable.thunderstorm // Good visual for small falling pellets

            // Thunderstorm Conditions
            "THUNDERSTORM", "THUNDERSHOWER", "LIGHT_THUNDERSTORM_RAIN", "SCATTERED_THUNDERSTORMS", "HEAVY_THUNDERSTORM" -> R.drawable.thunderstorm

            // Rounded Fallback
            else -> R.drawable.cloud // A safe default if a new/unknown condition appears
        }
    }
}