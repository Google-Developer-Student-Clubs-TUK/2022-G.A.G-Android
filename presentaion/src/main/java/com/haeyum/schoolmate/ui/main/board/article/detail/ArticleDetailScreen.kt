/*
 * Created by PangMoo on 2022/12/1
 */

package com.haeyum.schoolmate.ui.main.board.article.detail

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.ui.component.HeaderComponent
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun ArticleDetailScreen(onNavigateUp: () -> Unit) {
    val (reply, setReply) = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderComponent.Header(
            title = "프로그래밍 게시판",
            onBackClick = onNavigateUp
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            ArticleContent()
            SplitDivider()

            repeat(6) {
                ReplyContent()
                if (it < 5)
                    SplitDivider()
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 15.dp)
                .background(
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(horizontal = 14.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                value = reply,
                onValueChange = setReply,
                modifier = Modifier.weight(1f),
                textStyle = TextStyle(color = MaterialTheme.colors.surface, fontSize = 12.sp)
            ) { innerTextField ->
                if (reply.isEmpty())
                    Text(
                        text = "댓글을 입력하세요.",
                        color = MaterialTheme.colors.surface,
                        fontSize = 12.sp
                    )
                innerTextField()
            }

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(start = 7.dp)
                    .size(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "send"
                )
            }
        }
    }

    BackHandler(onBack = onNavigateUp)
}

@Composable
private fun SplitDivider() {
    Divider(
        modifier = Modifier.padding(top = 15.dp),
        color = if (isSystemInDarkTheme()) MaterialTheme.colors.surface else MaterialTheme.colors.secondaryVariant,
        thickness = (1.5).dp
    )
}

@Composable
private fun ArticleContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = CircleShape
                    )
            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "유광무", color = TextColor, fontSize = 14.sp)
                Text(
                    text = "11월 27일 19시 32분",
                    color = TextColor,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Text(
            text = "우리 과제 언제까지였죠...?",
            modifier = Modifier.padding(top = 15.dp),
            color = TextColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요" +
                    "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요" +
                    "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요" +
                    "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요" +
                    "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요" +
                    "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요" +
                    "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요" +
                    "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요" +
                    "기억이 나지 않습니다... 살려주세요 기억이 나지 않습니다... 살려주세요 ",
            modifier = Modifier.padding(top = 10.dp),
            color = TextColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = "댓글 6개",
            modifier = Modifier.padding(top = 20.dp),
            color = TextColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
private fun ReplyContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .padding(horizontal = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(
                        color = MaterialTheme.colors.secondaryVariant,
                        shape = CircleShape
                    )
            )
            Column(
                modifier = Modifier.padding(start = 10.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = "유광무", color = TextColor, fontSize = 10.sp)
                Text(
                    text = "11월 27일 19시 32분",
                    color = TextColor,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Text(
            text = "어느 한 컴퓨터 공학 과 학생이 유명한 교수님을 찾아가 물었다.\n" +
                    "\"재귀함수가 뭔가요?\"\n" +
                    "\"잘 들어보게. 옛날에 산 꼭대기에 현자가 있었어. 질문엔 모두 지혜롭게 \u2028대답해 주었지.\n" +
                    "그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어\"\n" +
                    "\"재귀함수가 뭔가요?\"\n" +
                    "\"잘 들어보게. 옛날에 산 꼭대기...",
            modifier = Modifier.padding(top = 15.dp),
            color = TextColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleDetailDarkPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            ArticleDetailScreen(onNavigateUp = {})
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ArticleDetailLightPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            ArticleDetailScreen(onNavigateUp = {})
        }
    }
}