/*
 * Created by PangMoo on 2022/12/8
 */

@file:OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)

package com.haeyum.schoolmate.ui.intro

import android.util.Log
import androidx.annotation.RawRes
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.accompanist.pager.rememberPagerState
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun OnboardingScreen(onNavigateToLogin: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(top = 22.dp, bottom = 30.dp)
    ) {
        val pagerState = rememberPagerState()

        TextButton(
            onClick = onNavigateToLogin,
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
            HorizontalPager(count = 3, state = pagerState) { page ->
                when (page) {
                    0 -> OnboardingContent(
                        resId = R.raw.lottie_onboarding_task,
                        title = "확인해요",
                        description = "오늘의 수업과 과제를 확인해야 하나요?\n홈에서 한 번에 확인하실 수 있어요"
                    )
                    1 -> OnboardingContent(
                        resId = R.raw.lottie_onboarding_communication,
                        title = "소통해요",
                        description = "같이 수업을 듣는 다른 친구들이 궁금하신가요?\n수업 친구들과 함께 소통할 수 있어요"
                    )
                    2 -> OnboardingContent(
                        resId = R.raw.lottie_onboarding_security,
                        title = "안전해요",
                        description = "로그인에 사용되는 정보는 이중 암호화되어\n개발자 역시 알 수 없어요"
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(CenterHorizontally)
                    .padding(top = 40.dp),
                contentAlignment = Center
            ) {
                Log.d("PangMoo", "pagerState.currentPageOffset: ${pagerState.currentPageOffset}")
                val indicatorOffsetX by animateDpAsState(targetValue = (-30).dp + pagerState.currentPage.dp * 30)

                Row {
                    Box(
                        modifier = Modifier
                            .size(width = 12.dp, height = 6.dp)
                            .background(
                                color = MaterialTheme.colors.secondaryVariant,
                                shape = RoundedCornerShape(100.dp)
                            )
                    )
                    Spacer(modifier = Modifier.size(width = 16.dp, height = 6.dp))
                    Box(
                        modifier = Modifier
                            .size(width = 12.dp, height = 6.dp)
                            .background(
                                color = MaterialTheme.colors.secondaryVariant,
                                shape = RoundedCornerShape(100.dp)
                            )
                    )
                    Spacer(modifier = Modifier.size(width = 16.dp, height = 6.dp))
                    Box(
                        modifier = Modifier
                            .size(width = 12.dp, height = 6.dp)
                            .background(
                                color = MaterialTheme.colors.secondaryVariant,
                                shape = RoundedCornerShape(100.dp)
                            )
                    )
                }
                Box(
                    modifier = Modifier
                        .offset(x = indicatorOffsetX)
                        .size(width = 24.dp, height = 6.dp)
                        .background(
                            color = MaterialTheme.colors.secondary,
                            shape = RoundedCornerShape(100.dp)
                        )
                )
            }
        }
        AnimatedVisibility(
            visible = pagerState.currentPage == 2,
            modifier = Modifier.align(BottomCenter),
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut()
        ) {
            Button(
                onClick = onNavigateToLogin,
                modifier = Modifier
                    .align(BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 30.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colors.secondary),
                contentPadding = PaddingValues(12.dp)
            ) {
                Text(
                    text = "시작하기",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
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
    SchoolmateTheme { OnboardingScreen(onNavigateToLogin = {}) }
}