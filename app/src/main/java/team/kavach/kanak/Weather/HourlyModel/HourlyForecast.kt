package team.kavach.kanak.Weather.HourlyModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.CloudQueue
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.SevereCold
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material.icons.filled.WbCloudy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.CloudQueue
import androidx.compose.material.icons.outlined.Grain
import androidx.compose.material.icons.outlined.SevereCold
import androidx.compose.material.icons.outlined.Thermostat
import androidx.compose.material.icons.outlined.Thunderstorm
import androidx.compose.material.icons.outlined.Umbrella
import androidx.compose.material.icons.outlined.WbCloudy
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import team.kavach.kanak.R



data class HourlyForecast(
    val forecastHours: List<ForecastHour>
) {
    fun getWeatherIcon(hourIndex : Int) : ImageVector {
        return when (forecastHours[hourIndex].weatherCondition.type) {
            // Clear Conditions
            "CLEAR" -> Icons.Outlined.WbSunny
            "MOSTLY_CLEAR" -> Icons.Outlined.WbSunny

            // Cloudy Conditions
            "PARTLY_CLOUDY" -> Icons.Outlined.WbCloudy
            "MOSTLY_CLOUDY" -> Icons.Outlined.Cloud
            "CLOUDY" -> Icons.Outlined.CloudQueue

            // Windy Conditions
            "WINDY" -> Icons.Outlined.Air
            "WIND_AND_RAIN" -> Icons.Outlined.Thunderstorm // Good representation for wind and rain

            // Rain Conditions
            "LIGHT_RAIN_SHOWERS", "CHANCE_OF_SHOWERS", "SCATTERED_SHOWERS", "RAIN_SHOWERS", "LIGHT_RAIN" -> Icons.Outlined.Grain
            "HEAVY_RAIN_SHOWERS", "LIGHT_TO_MODERATE_RAIN", "MODERATE_TO_HEAVY_RAIN", "RAIN", "HEAVY_RAIN", "RAIN_PERIODICALLY_HEAVY" -> Icons.Outlined.Umbrella

            // Snow Conditions
            "LIGHT_SNOW_SHOWERS", "CHANCE_OF_SNOW_SHOWERS", "SCATTERED_SNOW_SHOWERS", "SNOW_SHOWERS", "LIGHT_SNOW" -> Icons.Outlined.Grain
            "HEAVY_SNOW_SHOWERS", "LIGHT_TO_MODERATE_SNOW", "MODERATE_TO_HEAVY_SNOW", "SNOW", "HEAVY_SNOW", "SNOW_PERIODICALLY_HEAVY" -> Icons.Outlined.Grain
            "SNOWSTORM", "HEAVY_SNOW_STORM", "BLOWING_SNOW" -> Icons.Outlined.SevereCold

            // Mixed Conditions
            "RAIN_AND_SNOW" -> Icons.Outlined.AcUnit // Often represented by a snowflake icon

            // Hail Conditions
            "HAIL", "HAIL_SHOWERS" -> Icons.Outlined.Grain // Good visual for small falling pellets

            // Thunderstorm Conditions
            "THUNDERSTORM", "THUNDERSHOWER", "LIGHT_THUNDERSTORM_RAIN", "SCATTERED_THUNDERSTORMS", "HEAVY_THUNDERSTORM" -> Icons.Outlined.Thunderstorm

            // Outlined Fallback
            else -> Icons.Outlined.Thermostat // A safe default if a new/unknown condition appears
        }
    }
}