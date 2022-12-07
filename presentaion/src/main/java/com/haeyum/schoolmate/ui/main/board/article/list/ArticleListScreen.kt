/*
 * Created by PangMoo on 2022/12/1
 */

package com.haeyum.schoolmate.ui.main.board.article.list

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.schoolmate.supports.noRippleClickable
import com.haeyum.schoolmate.ui.component.HeaderComponent
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun ArticleListScreen(onNavigateToDetail: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderComponent.Header(
            title = "프로그래밍 게시판",
            onBackClick = {
                // TODO BACK
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(top = 5.dp)
        ) {
            repeat(15) {
                ArticleItem(
                    title = "우리 과제 언제까지였죠...?",
                    content = "기억이 나지 않습니다... 살려주세요",
                    writer = "유광무",
                    date = "11월 27일 19시 32분",
                    replyCount = 6,
                    onClick = {
                        onNavigateToDetail(it.toString())
                    }
                )

                if (it < 14) {
                    Divider(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        color = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.secondaryVariant,
                        thickness = (1.5).dp
                    )
                }
            }
        }
    }
}

@Composable
private fun ArticleItem(
    title: String,
    content: String,
    writer: String,
    date: String,
    replyCount: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick)
            .padding(horizontal = 30.dp)
            .padding(top = 15.dp, bottom = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = title, color = TextColor, fontSize = 15.sp, fontWeight = FontWeight.Medium)
            Text(
                text = content,
                color = TextColor,
                modifier = Modifier.padding(top = 2.dp),
                fontSize = 10.sp,
                fontWeight = FontWeight.Light
            )
            Text(
                text = buildAnnotatedString {
                    append(writer)
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraLight)) {
                        append(" $date")
                    }
                },
                modifier = Modifier.padding(top = 8.dp),
                color = TextColor,
                fontSize = 8.sp,
                fontWeight = FontWeight.Normal
            )
        }
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colors.surface, shape = RoundedCornerShape(8.dp))
                .then(
                    if (isSystemInDarkTheme()) Modifier else Modifier.border(
                        width = 1.dp,
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = RoundedCornerShape(12.dp)
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = replyCount.toString(),
                    color = TextColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "댓글",
                    color = TextColor,
                    fontSize = 6.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleListScreenDarkPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            ArticleListScreen(onNavigateToDetail = {})
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ArticleListScreenLightPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            ArticleListScreen(onNavigateToDetail = {})
        }
    }
}