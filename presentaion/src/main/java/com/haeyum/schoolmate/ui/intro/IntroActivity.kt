package com.haeyum.schoolmate.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.schoolmate.ui.intro.login.LoginScreen
import com.haeyum.schoolmate.ui.intro.onboarding.OnboardingScreen
import com.haeyum.schoolmate.ui.intro.splash.SplashScreen
import com.haeyum.schoolmate.ui.main.MainActivity
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IntroActivity : ComponentActivity() {
    val viewModel by viewModels<IntroViewModel>()

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
                    0 -> SplashScreen(
                        onRequireLogin = {
                            navigate = 1
                        },
                        onAlreadyLogin = {
                            finishAndStartMain()
                        },
                        onFinishEvent = {
                            finish()
                        }
                    )
                    1 -> OnboardingScreen {
                        navigate = 2
                    }
                    2 -> LoginScreen {
                        finishAndStartMain()
                    }
                    else -> {
                        finishAndStartMain()
                    }
                }

                LaunchedEffect(statusBarColor) {
                    systemUiController.setStatusBarColor(statusBarColor)
                }
            }
        }
    }

    private fun finishAndStartMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}