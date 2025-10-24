package team.kavach.kanak.Weather.Forecast

data class Forecastday(
    val date_epoch: Int,
    val day: Day,
    val hour: List<Hour>
)