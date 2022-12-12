package com.haeyum.schoolmate.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.schoolmate.ui.main.MainActivity
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolmateTheme {
                val systemUiController = rememberSystemUiController()
                val statusBarColor = MaterialTheme.colors.background

                var navigate by remember {
                    mutableStateOf(0)
                }

                when (navigate) {
                    0 -> OnboardingScreen {
                        navigate = 1
                    }
                    1 -> LoginScreen {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    else -> {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }

                LaunchedEffect(statusBarColor) {
                    systemUiController.setStatusBarColor(statusBarColor)
                }
            }
        }

//        startActivity(Intent(this, MainActivity::class.java))
//        finish()
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SchoolmateTheme {
        Greeting2("Android")
    }
}