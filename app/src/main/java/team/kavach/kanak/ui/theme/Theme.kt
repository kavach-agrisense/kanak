package team.kavach.kanak.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.toursafe.ui.theme.DMSansFontFamily
import com.example.toursafe.ui.theme.Typography

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xffb8bb26),
    secondary = Color(0xff98971a),
    tertiary = Color(0xff79740e)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xff98971a),
    secondary = Color(0xff79740e),
    tertiary = Color(0xffdde5c2),


    surface = Color(0xfffbf1c7),
    surfaceDim = Color(0xfff3eac7),
    onSurfaceVariant = Color(0xff654735),
    surfaceContainer = Color(0xfff9f5d7),
    onSurface = Color(0xff4f3829),
    surfaceTint = Color(0xffe4edc8),
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
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

        // darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}