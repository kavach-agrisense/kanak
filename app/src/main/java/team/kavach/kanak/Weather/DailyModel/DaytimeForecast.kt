package team.kavach.kanak.Weather.DailyModel

data class DaytimeForecast(
    val precipitation: Precipitation,
    val relativeHumidity: Int,
    val weatherCondition: WeatherCondition
)