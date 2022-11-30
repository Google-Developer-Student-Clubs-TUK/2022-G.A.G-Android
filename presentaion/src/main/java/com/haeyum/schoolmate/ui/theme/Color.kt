package com.haeyum.schoolmate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightBlue = Color(0xFF4564FF)
val DeepBlue = Color(0xFF3C5CFF)
val DarkBlue = Color(0xFF1F1F39)
val GrayBlue = Color(0xFF302E43)
val LightGray = Color(0xFFB8B8D2)
val Orange = Color(0xFFFF5C17)

val TextColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
