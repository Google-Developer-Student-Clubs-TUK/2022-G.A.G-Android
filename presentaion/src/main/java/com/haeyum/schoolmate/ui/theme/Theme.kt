package com.haeyum.schoolmate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.haeyum.schoolmate.R

private val DarkColorPalette = darkColors(
    primary = LightBlue,
    secondary = DeepBlue,
    secondaryVariant = LightGray,
    background = DarkBlue,
    surface = GrayBlue,
    error = Orange
)

private val LightColorPalette = lightColors(
    primary = LightBlue,
    secondary = DeepBlue,
    secondaryVariant = LightGray,
    background = Color.White,
    surface = Color.White,
    error = Orange
)

val PretendardFontFamily = FontFamily(
    Font(resId = R.font.pretendard_thin, weight = FontWeight.Thin),
    Font(resId = R.font.pretendard_light, weight = FontWeight.Light),
    Font(resId = R.font.pretendard_extra_light, weight = FontWeight.ExtraLight),
    Font(resId = R.font.pretendard_black, weight = FontWeight.Black),
    Font(resId = R.font.pretendard_regular, weight = FontWeight.Normal),
    Font(resId = R.font.pretendard_medium, weight = FontWeight.Medium),
    Font(resId = R.font.pretendard_semi_bold, weight = FontWeight.SemiBold),
    Font(resId = R.font.pretendard_bold, weight = FontWeight.Bold),
    Font(resId = R.font.pretendard_extra_bold, weight = FontWeight.ExtraBold),
)

@Composable
fun SchoolmateTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}