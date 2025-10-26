package team.kavach.kanak.Weather.Forecast

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.HelpOutline
import androidx.compose.material.icons.outlined.AcUnit
import androidx.compose.material.icons.outlined.Air
import androidx.compose.material.icons.outlined.Cloud
import androidx.compose.material.icons.outlined.Nightlight
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.Shower
import androidx.compose.material.icons.outlined.Thunderstorm
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.ui.graphics.vector.ImageVector
import team.kavach.kanak.R

data class ForecastInfo(
    val current: Current,
    val forecast: Forecast,
    val location: Location
) {
    fun weatherIcon(hour : Int): Int {
        return when (forecast.forecastday[0].hour[hour].condition.code) {
            // --- Clear/Sunny/Cloudy ---
            1000 -> if (forecast.forecastday[0].hour[hour].is_day == 1) R.drawable.sunny else R.drawable.cloudy_night // Sunny / Clear
            1003 -> if (forecast.forecastday[0].hour[hour].is_day == 1) R.drawable.cloudy_day else R.drawable.cloudy_night // Partly Cloudy
            1006, 1009 -> R.drawable.cloud// Cloudy / Overcast

            // --- Mist/Fog ---
            1030, 1135, 1147 -> R.drawable.windy // Mist / Fog / Freezing fog

            // --- Rain / Drizzle / Showers ---
            1063, 1150, 1153, 1180, 1183, 1186, 1189, 1192, 1195, 1240, 1243 -> R.drawable.water_drop // All general rain, drizzle, and moderate/heavy rain showers
            1246 -> R.drawable.umbrella // Torrential rain shower (Using Umbrella for emphasis on heavy protection)

            // --- Freezing Rain / Sleet / Ice ---
            1069, 1072, 1168, 1171, 1198, 1201, 1204, 1207, 1249, 1252, 1237, 1261, 1264 -> R.drawable.snowing_cold // Sleet, Freezing Drizzle/Rain, Ice Pellets, Hail Showers

            // --- Snow ---
            1066, 1114, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258 -> R.drawable.snow // All Snow, Patchy Snow, Blowing Snow, Blizzard

            // --- Thunderstorms ---
            1087, 1273, 1276 -> R.drawable.thunderstorm // Thunder / Rain with Thunder
            1279, 1282 -> R.drawable.snowing // Thunder / Snow with Thunder (Could use SevereCold, but Thunderstorm is the primary danger)

            // --- Fallback (Shouldn't happen with complete data) ---
            else -> R.drawable.cloud
        }
    }

    fun getWeatherIcon(hour : Int): ImageVector {

        val isDaytime = forecast.forecastday[0].hour[hour].is_day == 1

        return when (forecast.forecastday[0].hour[hour].condition.code) {
            // ☀️ Clear / Sunny
            1000 -> if (isDaytime) Icons.Outlined.WbSunny else Icons.Outlined.NightsStay

            // 🌤️ Partly cloudy
            1003 -> if (isDaytime) Icons.Outlined.Cloud else Icons.Outlined.Nightlight

            // ☁️ Cloudy / overcast
            1006, 1009 -> Icons.Outlined.Cloud

            // 🌫️ Mist / Fog
            1030, 1135, 1147 -> Icons.Outlined.Air

            // 🌧️ Light rain / drizzle
            1063, 1072, 1150, 1153, 1168, 1171, 1180, 1183 ->
                Icons.Outlined.Shower

            // 🌦️ Moderate / heavy rain
            1186, 1189, 1192, 1195, 1240, 1243, 1246 ->
                Icons.Outlined.Thunderstorm

            // 🌨️ Snow / sleet
            1066, 1114, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258 ->
                Icons.Outlined.AcUnit

            // ⛈️ Thunderstorm
            1087, 1273, 1276, 1279, 1282 ->
                Icons.Outlined.Thunderstorm

            // 🌪️ Other / unknown
            else -> Icons.AutoMirrored.Outlined.HelpOutline
        }
    }

    fun icon() : Int {
        return when (current.condition.code) {
            // --- Clear/Sunny/Cloudy ---
            1000 -> if (current.is_day == 1) R.drawable.sunny else R.drawable.cloudy_night // Sunny / Clear
            1003 -> if (current.is_day == 1) R.drawable.cloudy_day else R.drawable.cloudy_night // Partly Cloudy
            1006, 1009 ->  R.drawable.cloud// Cloudy / Overcast

            // --- Mist/Fog ---
            1030, 1135, 1147 -> R.drawable.windy // Mist / Fog / Freezing fog

            // --- Rain / Drizzle / Showers ---
            1063, 1150, 1153, 1180, 1183, 1186, 1189, 1192, 1195, 1240, 1243 -> R.drawable.water_drop // All general rain, drizzle, and moderate/heavy rain showers
            1246 -> R.drawable.umbrella // Torrential rain shower (Using Umbrella for emphasis on heavy protection)

            // --- Freezing Rain / Sleet / Ice ---
            1069, 1072, 1168, 1171, 1198, 1201, 1204, 1207, 1249, 1252, 1237, 1261, 1264 -> R.drawable.snowing_cold // Sleet, Freezing Drizzle/Rain, Ice Pellets, Hail Showers

            // --- Snow ---
            1066, 1114, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258 -> R.drawable.snow // All Snow, Patchy Snow, Blowing Snow, Blizzard

            // --- Thunderstorms ---
            1087, 1273, 1276 -> R.drawable.thunderstorm // Thunder / Rain with Thunder
            1279, 1282 -> R.drawable.snowing // Thunder / Snow with Thunder (Could use SevereCold, but Thunderstorm is the primary danger)

            // --- Fallback (Shouldn't happen with complete data) ---
            else -> R.drawable.cloud
        }
    }
}