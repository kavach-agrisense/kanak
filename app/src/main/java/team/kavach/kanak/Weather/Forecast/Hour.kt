package team.kavach.kanak.Weather.Forecast

data class Hour(
    val chance_of_rain: Int,
    val condition: Condition,
    val humidity: Int,
    val temp_c: Double,
    val time_epoch: Int,
    val is_day : Int
)