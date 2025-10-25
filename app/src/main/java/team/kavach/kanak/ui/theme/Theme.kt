package team.kavach.kanak.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private const val saturationDark = 0.1f
private val DarkColorScheme = darkColorScheme(
    primary = Color.hsl(160f, 0.2f, 0.3f),
    secondary = Color(0xff98971a),
    tertiary = Color(0xff79740e),

    background = Color.hsl(160f, saturationDark, 0.05f),
    onBackground = Color.hsl(160f, saturationDark, 0.80f),

    surface = Color.hsl(160f, saturationDark, 0.1f),
    onSurface = Color.hsl(160f, saturationDark, 0.85f),

    surfaceContainer = Color.hsl(160f, saturationDark, 0.15f),
    onSurfaceVariant = Color.hsl(160f, saturationDark, 0.95f),

    surfaceContainerLow = Color.hsl(160f, saturationDark, 0.13f),

    surfaceDim = Color.hsl(160f, saturationDark, 0.1f),
)

private val LightColorScheme = lightColorScheme(
    primary = Color.hsl(160f, 0.4f, 0.8f),
    /*secondary = Color(0xff286983),
    tertiary = Color(0xff907aa9),
    surface = Color(0xfffaf4ed),
    surfaceDim = Color(0xfff2e9e1),
    onSurfaceVariant = Color(0xff797593),
    surfaceContainer = Color(0xfffffaf3),
    onSurface = Color(0xff4f3829),
    surfaceTint = Color(0xffe4edc8),*/
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */

    background = Color.hsl(160f, 0.4f, 0.9f),
    onBackground = Color.hsl(160f, 0.4f, 0.15f),

    surface = Color.hsl(160f, 0.4f, 0.95f),
    onSurface = Color.hsl(160f, 0.4f, 0.1f),

    surfaceContainer = Color.hsl(160f, 0.4f, 1f),
    onSurfaceVariant = Color.hsl(160f, 0.4f, 0.15f),

    surfaceContainerLow = Color.hsl(160f, 0.4f, 0.97f),


    surfaceDim = Color.hsl(160f, 0.4f, 0.85f),
)

@Composable
fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }*/

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}