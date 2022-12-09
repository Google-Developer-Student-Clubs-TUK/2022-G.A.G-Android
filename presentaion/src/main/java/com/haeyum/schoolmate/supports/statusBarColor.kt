package com.haeyum.schoolmate.supports

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController

@Composable
fun statusBarColor(systemUiController : SystemUiController , color :Color) {
    if(isSystemInDarkTheme()){
        systemUiController.setSystemBarsColor(
            color = color
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = color
        )
    }
}