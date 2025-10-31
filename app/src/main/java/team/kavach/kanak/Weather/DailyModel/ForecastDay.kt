package team.kavach.kanak.Weather.DailyModel

data class ForecastDay(
    val daytimeForecast: DaytimeForecast,
    val displayDate: DisplayDate,
    val maxTemperature: MaxTemperature,
    val minTemperature: MinTemperature,
    val nighttimeForecast: NighttimeForecast
)