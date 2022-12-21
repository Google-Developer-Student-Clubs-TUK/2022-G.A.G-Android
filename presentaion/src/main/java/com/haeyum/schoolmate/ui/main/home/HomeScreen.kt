package com.haeyum.schoolmate.ui.main.home


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.haeyum.domain.data.notifyTime.NotifyTime
import com.haeyum.domain.data.timeSchedule.TimeSchedule
import com.haeyum.schoolmate.data.Home.TimeScheduleDto
import com.haeyum.schoolmate.data.Home.TodoDto
import com.haeyum.schoolmate.supports.drawBorder
import com.haeyum.schoolmate.ui.theme.LightBlue
import com.haeyum.schoolmate.ui.theme.Orange
import com.haeyum.schoolmate.ui.theme.TextColor
import utils.TimeUtil

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

//    val systemUiController = rememberSystemUiController()
//    LaunchedEffect(true) {
//        statusBarColor(systemUiController, Color.Transparent)
//    }

    SideEffect {
        viewModel.getData()
    }

    val timeScheduleInfo = viewModel.timeScheduleInfo.value
    val todoInfo = viewModel.todoInfo.value
    val notifyTimeInfo = viewModel.notifyTimeInfo.value

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        val (profileRef, contentRef, nextTimetableRef) = createRefs()
        ProfileFrame(profileRef)
        NextTimeTableFrame(nextTimetableRef, contentRef, profileRef, notifyTimeInfo)
        ContentFrame(contentRef, profileRef, timeScheduleInfo, todoInfo)
    }

}

@Composable
private fun ConstraintLayoutScope.ContentFrame(
    contentRef: ConstrainedLayoutReference,
    profileRef: ConstrainedLayoutReference,
    timeScheduleInfo: List<TimeSchedule>,
    todoInfo: List<TodoDto>
) {
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

@Composable
private fun ConstraintLayoutScope.NextTimeTableFrame(
    nextTimetableRef: ConstrainedLayoutReference,
    scheduleRef: ConstrainedLayoutReference,
    profileRef: ConstrainedLayoutReference,
    notifyTimeInfo: NotifyTime
) {
    Column(modifier = Modifier
        .zIndex(1f)
        .constrainAs(nextTimetableRef) {
            top.linkTo(scheduleRef.top)
            bottom.linkTo(profileRef.bottom)
        }
        .padding(horizontal = 30.dp)
        .background(
            MaterialTheme.colors.surface,
            shape = RoundedCornerShape(12.dp)
        )
        .drawBorder()
        .padding(20.dp),
        verticalArrangement = Arrangement.Center) {
        NextTimetable(notifyTimeInfo)
    }

}

@Composable
private fun ConstraintLayoutScope.ProfileFrame(profileRef: ConstrainedLayoutReference) {
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


@Composable
private fun Contents(
    timeScheduleInfo: List<TimeSchedule>,
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
private fun TimeSchedule(timeScheduleInfo: List<TimeSchedule>) {
    Text(
        text = "오늘 시간표",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = TextColor,
        modifier = Modifier.padding(bottom = 20.dp)
    )
    Column(
        modifier = Modifier
            .background(
                MaterialTheme.colors.surface, shape = RoundedCornerShape(12.dp)
            )
            .drawBorder()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        timeScheduleInfo.forEach { data ->
            TimeScheduleList(data)
        }
    }

}

@Composable
private fun NextTimetable(notifyTimeInfo: NotifyTime) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = notifyTimeInfo.message,
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor
        )
        notifyTimeInfo.time?.let {
            Text(
                text = it,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.primary
            )
        }
    }

    if(notifyTimeInfo.isLeft) {
        Text(
            text = notifyTimeInfo.major + " " + notifyTimeInfo.leftTime,
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
private fun TimeScheduleList(data: TimeSchedule) {
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
                text = data.room,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = TextColor
            )
            Text(
                text = data.name,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextColor
            )
        }
        Text(
            text = TimeUtil.toStringMS(data.startTime)+"-"+TimeUtil.toStringMS(data.endTime),
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            color = TextColor
        )
    }
}


@Composable
fun TodoList(index: Int, data: TodoDto) {

    val color = remember(data.is_submit) { (if (data.is_submit) Orange else LightBlue) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colors.surface,
                shape = RoundedCornerShape(12.dp)
            )
            .drawBorder()
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
                    .background(
                        color = color,
                        shape = CircleShape
                    ),
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

