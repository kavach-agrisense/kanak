package team.kavach.kanak.Weather

import androidx.compose.ui.graphics.vector.ImageVector

data class Condition(
    val code: Int,
    val icon: String,
    val text: String
)