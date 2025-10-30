package team.kavach.kanak.Weather.HourlyModel

data class ForecastHour(
    val displayDateTime: DisplayDateTime,
    val precipitation: Precipitation,
    val relativeHumidity: Int,
    val temperature: Temperature,
    val weatherCondition: WeatherCondition
)