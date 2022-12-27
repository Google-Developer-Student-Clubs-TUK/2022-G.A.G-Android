package com.haeyum.schoolmate.ui.main.profile

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.haeyum.domain.data.profile.Profile
import com.haeyum.schoolmate.ui.component.HeaderComponent
import com.haeyum.schoolmate.ui.component.ToggleComponent
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    val profile = viewModel.profile.collectAsState().value

    PureProfileScreen(profile)
}

@Composable
private fun PureProfileScreen(profile: Profile?) {
    val (enabledProfile, setEnabledProfile) = remember {
        mutableStateOf(false)
    }
    val (enabledNotification, setEnabledNotification) = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.Companion
            .fillMaxSize(1f)
            .padding(horizontal = 30.dp)
    ) {
        HeaderComponent.LargeHeader(title = "프로필", description = "나의 멋진 설명을 담아보았어요")
        Profile(profile = profile)
        Spacer(modifier = Modifier.height(20.dp))
        Section(text = "설정", isLoading = profile == null) {
            ToggleItem(
                key = "프로필 사진 노출",
                value = enabledProfile,
                onValueChange = setEnabledProfile
            )
            ToggleItem(
                key = "게시글/댓글 알림",
                value = enabledNotification,
                onValueChange = setEnabledNotification
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Tech University of Korea\nGoogle Developer Student Clubs",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            color = MaterialTheme.colors.secondaryVariant,
            fontSize = 10.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun Profile(profile: Profile?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .placeholder(
                visible = profile == null,
                highlight = PlaceholderHighlight.shimmer()
            ),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // TODO : 차후 프로필 API 나오면 사진 연동
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(color = MaterialTheme.colors.secondaryVariant, shape = CircleShape)
        )
        Column {
            Text(
                text = profile?.name ?: "-",
                modifier = Modifier
                    .padding(top = 2.dp)
                    .fillMaxWidth(),
                color = TextColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = profile?.id ?: "-",
                modifier = Modifier
                    .fillMaxWidth(),
                color = TextColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = profile?.major ?: "-",
                modifier = Modifier
                    .fillMaxWidth(),
                color = TextColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
private fun Section(text: String, isLoading: Boolean, content: @Composable ColumnScope.() -> Unit) {
    Divider(
        modifier = Modifier.padding(top = 10.dp),
        color = MaterialTheme.colors.surface,
        thickness = (1.5).dp
    )
    Text(
        text = text,
        modifier = Modifier.padding(top = 10.dp),
        color = TextColor,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )
    Column(
        modifier = Modifier
            .padding(top = 15.dp)
            .placeholder(
                visible = isLoading,
                highlight = PlaceholderHighlight.shimmer()
            ),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        content = content
    )
}

@Composable
private fun ToggleItem(
    key: String,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = key, color = TextColor, fontSize = 14.sp)
        ToggleComponent.Toggle(value = value, onValueChange = onValueChange)
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenCompleteDarkPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            PureProfileScreen(
                profile = Profile(
                    name = "유광무",
                    email = "vnycall74@naver.com",
                    imageUrl = "https://haeyum.dev/pangmoo/profile.jpeg",
                    id = "2019156023",
                    major = "컴퓨터공학부 소프트웨어 전공",
                    isAlarmOn = true,
                    isProfileVisible = true,
                )
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProfileScreenLoadingDarkPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            PureProfileScreen(profile = null)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ProfileScreenCompleteLightPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            PureProfileScreen(
                profile = Profile(
                    name = "유광무",
                    id = "2019156023",
                    email = "vnycall74@naver.com",
                    imageUrl = "https://haeyum.dev/pangmoo/profile.jpeg",
                    major = "컴퓨터공학부 소프트웨어 전공",
                    isAlarmOn = true,
                    isProfileVisible = true,
                )
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ProfileScreenLoadingLightPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            PureProfileScreen(profile = null)
        }
    }
}