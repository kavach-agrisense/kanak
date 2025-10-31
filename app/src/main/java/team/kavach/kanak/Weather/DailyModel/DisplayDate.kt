package team.kavach.kanak.Weather.DailyModel

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.decapitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import java.time.LocalDate
import java.time.LocalDateTime

data class DisplayDate(
    val day: Int,
    val month: Int,
    val year: Int
) {
    private fun localDate() : LocalDate {
        return LocalDate.of(year, month, day)
    }

    fun day() : String {
        val today = LocalDateTime.now().toLocalDate();

        return when (this.localDate()) {
            today -> "Today"
            today.plusDays(1) -> "Tomorrow"
            else -> localDate().dayOfWeek.toString().lowercase().capitalize(Locale.current)
        }
    }

}