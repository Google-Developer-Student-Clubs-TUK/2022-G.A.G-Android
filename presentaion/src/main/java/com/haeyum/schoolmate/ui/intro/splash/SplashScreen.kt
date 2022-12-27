/*
 * Created by PangMoo on 2022/12/27
 */

package com.haeyum.schoolmate.ui.intro.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeyum.schoolmate.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onRequireLogin: () -> Unit,
    onAlreadyLogin: () -> Unit,
    onFinishEvent: () -> Unit
) {
    val errorState = viewModel.errorEvent.collectAsState(null).value

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(160.dp)
        )
    }

    AnimatedVisibility(visible = errorState != null) {
        AlertDialog(
            onDismissRequest = { /*no-op*/ },
            buttons = {
                TextButton(onClick = onFinishEvent) {
                    Text("종료")
                }
            }, title = {
                Text(errorState?.first ?: "오류")
            }, text = {
                Text(text = errorState?.second ?: "오류가 발생했습니다.")
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.loginStatusEvent.collectLatest {
            when (it) {
                SplashViewModel.SplashLoginStatusEvent.RequireLogin -> onRequireLogin()
                SplashViewModel.SplashLoginStatusEvent.AlreadyLogin -> onAlreadyLogin()
            }
        }
    }
}