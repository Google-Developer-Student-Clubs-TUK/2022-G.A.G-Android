package com.haeyum.schoolmate.ui.main

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.ui.main.model.BottomNavigationState
import com.haeyum.schoolmate.ui.main.navigation.MainNavRoute
import com.haeyum.schoolmate.ui.main.navigation.SetupMainNavGraph
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(), onFinishEvent: () -> Unit) {
    val navController = rememberNavController()

    val (bottomNavigationState, setBottomNavigationState) = remember {
        mutableStateOf<BottomNavigationState>(BottomNavigationState.Home)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(MaterialTheme.colors.background)
            .imePadding()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            val isShowBackPressSnackbar =
                viewModel.isShowBackPressSnackbar.collectAsState().value

            Column(modifier = Modifier.fillMaxSize()) {
                SetupMainNavGraph(navHostController = navController)
            }

            androidx.compose.animation.AnimatedVisibility(
                visible = isShowBackPressSnackbar,
                modifier = Modifier.align(Alignment.BottomCenter),
                enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
            ) {
                Snackbar(
                    modifier = Modifier.padding(15.dp),
                    backgroundColor = MaterialTheme.colors.secondary
                ) {
                    Text(text = "뒤로가기를 한번 더 누르면 종료됩니다.", color = Color.White, fontSize = 14.sp)
                }
            }
        }
        BottomNavigation(
            state = bottomNavigationState,
            onStateChange = setBottomNavigationState
        )
    }

    BackHandler {
        viewModel.backPress()
    }

    LaunchedEffect(bottomNavigationState) {
        when (bottomNavigationState) {
            BottomNavigationState.Home -> navController.navigate(MainNavRoute.Home.route)
            BottomNavigationState.Board -> navController.navigate(MainNavRoute.Board.route)
            BottomNavigationState.Profile -> navController.navigate(MainNavRoute.Profile.route)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.finishEvent.collectLatest {
            onFinishEvent()
        }
    }
}

@Composable
private fun BottomNavigation(
    state: BottomNavigationState = BottomNavigationState.Home,
    onStateChange: (BottomNavigationState) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(5.dp)
            .background(MaterialTheme.colors.surface),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavigationItem(
            state = BottomNavigationState.Home,
            currentState = state,
            text = "홈",
            resId = R.drawable.ic_home,
            onStateChange = onStateChange
        )
        BottomNavigationItem(
            state = BottomNavigationState.Board,
            currentState = state,
            text = "과목 게시판",
            resId = R.drawable.ic_board,
            onStateChange = onStateChange
        )
        BottomNavigationItem(
            state = BottomNavigationState.Profile,
            currentState = state,
            text = "프로필",
            resId = R.drawable.ic_profile,
            onStateChange = onStateChange
        )
    }
}

@Composable
private fun BottomNavigationItem(
    state: BottomNavigationState,
    currentState: BottomNavigationState,
    text: String,
    @DrawableRes resId: Int,
    onStateChange: (BottomNavigationState) -> Unit = {}
) {
    IconButton(
        onClick = {
            onStateChange(state)
        }
    ) {
        Image(
            painter = painterResource(resId),
            contentDescription = text,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(
                if (state == currentState) {
                    MaterialTheme.colors.secondary
                } else {
                    MaterialTheme.colors.secondaryVariant
                }
            )
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun MainScreenLightPreview() {
    MainScreen(viewModel = MainViewModel(), onFinishEvent = {})
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenDarkPreview() {
    MainScreen(viewModel = MainViewModel(), onFinishEvent = {})
}
