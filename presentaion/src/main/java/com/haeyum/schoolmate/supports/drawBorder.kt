package com.haeyum.schoolmate.supports

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

@Composable
fun drawBorder(): Modifier {
    return if (isSystemInDarkTheme()) Modifier else Modifier.border(
        width = 1.dp,
        color = MaterialTheme.colors.secondaryVariant,
        shape = RoundedCornerShape(12.dp)
    )


}