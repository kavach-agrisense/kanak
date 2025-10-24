package team.kavach.kanak.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun verticalGradientBrush() : Brush {
    return Brush.verticalGradient(listOf(
        MaterialTheme.colorScheme.surfaceContainer,
        MaterialTheme.colorScheme.surface
    ))
}

@Composable
fun inverseVerticalGradientBrush() : Brush {
    return Brush.verticalGradient(listOf(
        MaterialTheme.colorScheme.surface,
        MaterialTheme.colorScheme.surfaceContainer,
    ))
}