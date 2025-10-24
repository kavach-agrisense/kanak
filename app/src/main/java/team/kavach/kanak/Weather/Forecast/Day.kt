package team.kavach.kanak.Weather.Forecast

data class Day(
    val avghumidity: Int,
    val avgtemp_c: Double,
    val condition: Condition,
    val daily_chance_of_rain: Int,
    val maxtemp_c: Double,
    val mintemp_c: Double
)