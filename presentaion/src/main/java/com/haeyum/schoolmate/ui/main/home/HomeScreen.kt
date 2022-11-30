package com.haeyum.schoolmate.ui.main.home

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.toy_proejct.data.TimeScheduel
import com.example.toy_proejct.data.Todo
import com.example.toy_proejct.data.product.list.ProductListDto

import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonNull.content


@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val tempData: List<TimeScheduel> = listOf(
        TimeScheduel(location = "산융 205호", major = "수학2", time = "11:30~12:20"),
        TimeScheduel(location = "산융 206호", major = "수학2", time = "11:30~12:20"),
    )


    val tempTodo: List<Todo> = listOf(
        Todo(
            name = "13주차 교제 과제",
            major = "영어",
            time = "11.21 오전 11:00",
            is_submit = false
        ),
        Todo(
            name = "13주차 교제 과제",
            major = "영어",
            time = "11.21 오전 11:00",
            is_submit = false
        ),
        Todo(
            name = "13주차 교제 과제",
            major = "영어",
            time = "11.21 오전 11:00",
            is_submit = true
        ),
        Todo(
            name = "13주차 교제 과제",
            major = "영어",
            time = "11.21 오전 11:00",
            is_submit = true
        ),
    )

    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

        val layout1 = createRef()
        val layout2 = createRef()
        val anounceNextTime = createRef()


        Column(
            modifier = Modifier
                .background(color = Color(0xFF3C5CFF))
                .fillMaxWidth()
                .padding(top = 44.dp, bottom = 67.dp)
                .padding(horizontal = 30.dp)
                .constrainAs(layout1) {
                }

            //padding값으로 높이를 조절하기 -> 내부 요소값이 아무리 바뀌어도 상대적으로 조절 가능
            //또는 FilmaxHeight로 퍼센트값 주기

        ) {
            UserInfo()
        }
        Column(modifier = Modifier
            .zIndex(1f)
            .constrainAs(anounceNextTime) {
                top.linkTo(layout2.top)
                bottom.linkTo(layout1.bottom)
            }
            .padding(horizontal = 30.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            //패딩을 먼저주고 배경색지정. 그리고 내부 패딩지정
            .background(color = Color(0xFF302E43))
            .padding(20.dp),
            verticalArrangement = Arrangement.Center) {
            announceLesson()
        }

        Column(
            modifier = Modifier
                .background(color = Color(0xFF1F1F39))
                .fillMaxWidth()
                .padding(top = 80.dp)
                .constrainAs(layout2) {
                    top.linkTo(layout1.bottom)
                }, content = contents(tempData, tempTodo)

        )
    }

}

@Composable
private fun contents(
    tempData: List<TimeScheduel>,
    tempTodo: List<Todo>
): @Composable() (ColumnScope.() -> Unit) =
    {
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = "오늘 시간표",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = Color(0xFF302E43))
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                tempData.forEach { data ->
                    TimeScheduelContent(data)
                }
            }

            Text(
                text = "오늘 할일",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 20.dp, top = 30.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                tempTodo.forEachIndexed() { index, data ->
                    TodoList(index, data)
                }
            }

        }
    }

@Composable
private fun announceLesson() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "다음 수업까지",
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White

        )
        Text(
            text = "오후 3시 20분",
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = Color(0x3C5CFF)
        )
    }
    Text(
        text = "프로그래밍 10분",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )
}

@Composable
private fun UserInfo() {
    Text(
        text = "19 유광무",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
    )
    Text(
        text = "컴퓨터공학부 소프트웨어전공",
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = Color.White
    )
}


@Composable
fun TimeScheduelContent(data: TimeScheduel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = data.location,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
            )
            Text(
                text = data.major,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Text(
            text = data.time,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
        )
    }

}


@Composable
fun TodoList(index: Int, data: Todo) {

    //boolean값에 따라 색과 Text or 파란 동그라미를 그려야 한다.

    // 1. 아얘 다른 컴포넌트로 분리하기 (근데 공통되는 부분이 있다.)
    // 2. 다른 부분만 if문으로 분기해서 나타내기 (이게맞는듯?)

    val color = if (data.is_submit) Color(0xFF3C5CFF) else Color(0xFFFF5C17)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = Color(0xFF302E43))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .clip(CircleShape)
                    .background(color = color),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = (index + 1).toString(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
            Column() {
                Text(
                    text = data.name,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,

                    )
                Text(
                    text = data.major + "과제",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )
            }
        }

        if (data.is_submit) {
            Canvas(modifier = Modifier.size(20.dp), onDraw = {
                drawCircle(color = color)
            })
        } else {
            Text(
                text = data.time,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
            )
        }

    }
}

