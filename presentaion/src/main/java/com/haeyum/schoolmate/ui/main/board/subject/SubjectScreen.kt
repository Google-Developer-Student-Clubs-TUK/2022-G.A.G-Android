/*
 * Created by PangMoo on 2022/12/1
 */

package com.haeyum.schoolmate.ui.main.board.subject

import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.schoolmate.R
import com.haeyum.schoolmate.ui.component.HeaderComponent
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun SubjectScreen(onNavigateToArticleList: (subjectId: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        HeaderComponent.LargeHeader(
            title = "과목 게시판",
            description = "같이 수업을 듣는 친구들과 소통을 해보세요"
        )
        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .weight(1f)
                .padding(top = 15.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            repeat(15) {
                SubjectItem(name = "프로그래밍") {
                    onNavigateToArticleList(it.toString())
                }
            }
            Spacer(modifier = Modifier.size(15.dp))
        }
    }
}

@Composable
private fun SubjectItem(name: String, onClick: () -> Unit) {
    var isPinned by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.surface,
                shape = RoundedCornerShape(12.dp)
            )
            .then(
                if (isSystemInDarkTheme()) Modifier else Modifier.border(
                    width = 1.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = RoundedCornerShape(12.dp)
                )
            )
            .clickable(onClick = onClick)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { isPinned = !isPinned }, modifier = Modifier.size(24.dp)) {
            Image(
                painter = painterResource(id = if (isPinned) R.drawable.ic_pin_on else R.drawable.ic_pin_off),
                contentDescription = null
            )
        }
        Text(
            text = name,
            modifier = Modifier.padding(start = 20.dp),
            color = TextColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SubjectScreenDarkPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            SubjectScreen(onNavigateToArticleList = {})
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SubjectScreenLightPreview() {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            SubjectScreen(onNavigateToArticleList = {})
        }
    }
}