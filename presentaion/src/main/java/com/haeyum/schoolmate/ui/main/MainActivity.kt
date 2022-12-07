package com.haeyum.schoolmate.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolmateTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = MaterialTheme.colors.background

                MainScreen(onFinishEvent = ::finish)

                LaunchedEffect(statusBarColor) {
                    systemUiController.setStatusBarColor(statusBarColor)
                }
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}