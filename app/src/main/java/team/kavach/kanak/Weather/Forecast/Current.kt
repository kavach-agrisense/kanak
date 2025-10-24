package team.kavach.kanak.Weather.Forecast

data class Current(
    val condition: Condition,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_mm: Int,
    val temp_c: Double,
    val wind_dir: String,
    val wind_kph: Float
)