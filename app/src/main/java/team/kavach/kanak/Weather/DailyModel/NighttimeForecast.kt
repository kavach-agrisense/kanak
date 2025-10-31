package team.kavach.kanak.Weather.DailyModel

data class NighttimeForecast(
    val precipitation: Precipitation,
    val relativeHumidity: Int,
    val weatherCondition: WeatherCondition
)