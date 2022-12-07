package com.haeyum.schoolmate.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolmateTheme {
                MainScreen(onFinishEvent = ::finish)
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}