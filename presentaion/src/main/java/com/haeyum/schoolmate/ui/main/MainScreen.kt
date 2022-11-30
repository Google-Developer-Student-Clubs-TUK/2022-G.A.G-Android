package com.haeyum.schoolmate.ui.main

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.ui.main.model.BottomNavigationState
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme

@Composable
fun MainScreen() {
    SchoolmateTheme {
        val (bottomNavigationState, setBottomNavigationState) = remember {
            mutableStateOf<BottomNavigationState>(BottomNavigationState.Home)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Home Screen
                // Board Screen
                // Profile Screen
            }
            BottomNavigation(
                state = bottomNavigationState,
                onStateChange = setBottomNavigationState
            )
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
    MainScreen()
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainScreenDarkPreview() {
    MainScreen()
}
