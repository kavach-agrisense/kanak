package team.kavach.kanak.Weather.Forecast

data class Forecast(
    val current: Current,
    val forecast: ForecastX,
    val location: Location
)