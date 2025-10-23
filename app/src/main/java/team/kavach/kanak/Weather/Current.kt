package team.kavach.kanak.Weather

data class Current(
    val cloud: Int,
    val condition: Condition,

    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val last_updated_epoch: Int,
    val precip_mm: Int,
    val temp_c: Float,
    val temp_f: Float,
    val wind_dir: String,
    val wind_kph: Float,
)