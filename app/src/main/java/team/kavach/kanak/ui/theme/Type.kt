package team.kavach.kanak.ui.theme

import team.kavach.kanak.R;
import androidx.compose.material3.Typography
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

val DMSansFontFamily = FontFamily (
    Font(R.font.dmsans_black, FontWeight.Black),
    Font(R.font.dmsans_blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.dmsans_bold, FontWeight.Bold),
    Font(R.font.dmsans_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.dmsans_extrabold, FontWeight.ExtraBold),
    Font(R.font.dmsans_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.dmsans_extralight, FontWeight.ExtraLight),
    Font(R.font.dmsans_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.dmsans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.dmsans_light, FontWeight.Light),
    Font(R.font.dmsans_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.dmsans_medium, FontWeight.Medium),
    Font(R.font.dmsans_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.dmsans_regular, FontWeight.Normal),
    Font(R.font.dmsans_semibold, FontWeight.SemiBold),
    Font(R.font.dmsans_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.dmsans_thin, FontWeight.Thin),
    Font(R.font.dmsans_thinitalic, FontWeight.Thin, FontStyle.Italic)
);
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = DMSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

