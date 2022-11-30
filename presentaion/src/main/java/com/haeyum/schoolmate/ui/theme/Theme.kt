package com.haeyum.schoolmate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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