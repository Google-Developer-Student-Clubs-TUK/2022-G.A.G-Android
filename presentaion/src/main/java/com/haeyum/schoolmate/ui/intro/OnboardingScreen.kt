/*
 * Created by PangMoo on 2022/12/8
 */

@file:OptIn(ExperimentalPagerApi::class)

package com.haeyum.schoolmate.ui.intro

import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun OnboardingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 22.dp, bottom = 30.dp)
    ) {
        TextButton(
            onClick = {

            },
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(end = 22.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            Text(text = "Skip", color = TextColor, fontSize = 14.sp, fontWeight = FontWeight.Light)
        }
        Column(
            modifier = Modifier
                .align(Center)
                .padding(bottom = 30.dp)
        ) {
            HorizontalPager(count = 3) { index ->
                when (index) {
                    0 -> OnboardingContent(
                        resId = R.raw.onboarding_task,
                        title = "확인하세요",
                        description = "오늘의 수업과 과제를 확인해야 하나요?\n홈에서 한 번에 확인하실 수 있어요"
                    )
                    1 -> OnboardingContent(
                        resId = R.raw.onboarding_communication,
                        title = "소통하세요",
                        description = "같이 수업을 듣는 다른 친구들이 궁금하신가요?\n수업 친구들과 함께 소통할 수 있어요"
                    )
                    2 -> OnboardingContent(
                        resId = R.raw.onboarding_security,
                        title = "안전해요",
                        description = "로그인에 사용되는 정보는 이중 암호화되어\n개발자 역시 알 수 없어요"
                    )
                }
            }
        }
    }
}

@Composable
private fun OnboardingContent(@RawRes resId: Int, title: String, description: String) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(resId))

    Column(horizontalAlignment = CenterHorizontally) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .aspectRatio(1.0f),
            iterations = LottieConstants.IterateForever
        )
        Text(
            text = title,
            modifier = Modifier.padding(top = 30.dp),
            color = TextColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 25.dp),
            color = TextColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
    SchoolmateTheme { OnboardingScreen() }
}