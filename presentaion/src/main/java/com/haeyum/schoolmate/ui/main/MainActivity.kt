package com.haeyum.schoolmate.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.di.SchoolMateApp
import com.haeyum.schoolmate.ui.theme.DarkBlue
import com.haeyum.schoolmate.ui.theme.DeepBlue
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()

            MainScreen()
            LaunchedEffect(Unit) {
                systemUiController.setStatusBarColor(DarkBlue)
            }
        }
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}