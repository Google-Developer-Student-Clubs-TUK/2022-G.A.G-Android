package com.haeyum.schoolmate.ui.main.home


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.haeyum.schoolmate.data.Home.TimeScheduleDto
import com.haeyum.schoolmate.data.Home.TodoDto
import com.haeyum.schoolmate.supports.drawBorder
import com.haeyum.schoolmate.supports.statusBarColor
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    statusBarColor(rememberSystemUiController() , Color.Transparent)


    viewModel.getData()
    val timeScheduleInfo = viewModel.timeScheduleInfo.value
    val todoInfo = viewModel.todoInfo.value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        val profile = createRef()
        val content = createRef()
        val nextTimetable = createRef()

        ProfileFrame(profile)
        NextTimeTableFrame(nextTimetable, content, profile)
        ContentFrame(content, profile, timeScheduleInfo, todoInfo)
    }

}

@Composable
private fun ConstraintLayoutScope.ContentFrame(
    contentRef: ConstrainedLayoutReference,
    profileRef: ConstrainedLayoutReference,
    timeScheduleInfo: List<TimeScheduleDto>,
    todoInfo: List<TodoDto>
) {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxWidth()
                .padding(top = 80.dp)
                .constrainAs(contentRef) {
                    top.linkTo(profileRef.bottom)
                }, content = Contents(timeScheduleInfo, todoInfo)

        )
    }
}

@Composable
private fun ConstraintLayoutScope.NextTimeTableFrame(
    nextTimetableRef: ConstrainedLayoutReference,
    scheduleRef: ConstrainedLayoutReference,
    profileRef: ConstrainedLayoutReference
) {
    SchoolmateTheme {
        Column(modifier = Modifier
            .zIndex(1f)
            .constrainAs(nextTimetableRef) {
                top.linkTo(scheduleRef.top)
                bottom.linkTo(profileRef.bottom)
            }
            .padding(horizontal = 30.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.surface)
            .then(
                drawBorder()
            )
            .padding(20.dp),
            verticalArrangement = Arrangement.Center) {
            NextTimetable()
        }
    }
}

@Composable
private fun ConstraintLayoutScope.ProfileFrame(profileRef: ConstrainedLayoutReference) {
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .fillMaxWidth()
                .padding(top = 44.dp, bottom = 67.dp)
                .padding(horizontal = 30.dp)
                .constrainAs(profileRef) {}

        ) {
            UserInfo()
        }
    }
}


@Composable
private fun Contents(
    timeScheduleInfo: List<TimeScheduleDto>,
    todoInfo: List<TodoDto>
): @Composable() (ColumnScope.() -> Unit) =
    {
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TimeSchedule(timeScheduleInfo)
            Todo(todoInfo)
        }

    }

@Composable
private fun Todo(todoInfoList: List<TodoDto>) {
    Text(
        text = "오늘 할일",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor,
        modifier = Modifier.padding(bottom = 20.dp, top = 30.dp)
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        todoInfoList.forEachIndexed() { index, data ->
            TodoList(index, data)
        }
    }
}

@Composable
private fun TimeSchedule(timeScheduleInfo: List<TimeScheduleDto>) {
    Text(
        text = "오늘 시간표",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor,
        modifier = Modifier.padding(bottom = 20.dp)
    )
    SchoolmateTheme {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.surface)
                .then(
                    drawBorder()
                )
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            timeScheduleInfo.forEach { data ->
                TimeScheduleList(data)
            }
        }
    }
}

@Composable
private fun NextTimetable() {
    SchoolmateTheme {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "다음 수업까지",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = TextColor

            )
            Text(
                text = "오후 3시 20분",
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.primary
            )
        }
        Text(
            text = "프로그래밍 10분",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextColor,
        )
    }
}

@Composable
private fun UserInfo() {
    Text(
        text = "19 유광무",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor,
    )
    Text(
        text = "컴퓨터공학부 소프트웨어전공",
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        color = TextColor
    )
}


@Composable
private fun TimeScheduleList(data: TimeScheduleDto) {
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
                color = TextColor
            )
            Text(
                text = data.major,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )
        }
        Text(
            text = data.time,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor
        )
    }

}


@Composable
fun TodoList(index: Int, data: TodoDto) {


    val color = if (data.is_submit) Color(0xFF3C5CFF) else Color(0xFFFF5C17)
    SchoolmateTheme {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.surface)
                .then(
                    drawBorder()
                )
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
                        color = TextColor,
                    )
                }
                Column() {
                    Text(
                        text = data.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextColor,

                        )
                    Text(
                        text = data.major + "과제",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextColor,
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
                    color = TextColor,
                )
            }

        }
    }
}

