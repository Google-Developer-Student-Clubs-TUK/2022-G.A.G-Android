package com.haeyum.schoolmate.ui.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.haeyum.data.model.timeSchedule.TimeScheduleEntity
import com.haeyum.domain.data.notifyTime.NotifyTime
import com.haeyum.domain.data.timeSchedule.TimeSchedule
import com.haeyum.domain.data.todo.Todo
import com.haeyum.schoolmate.data.Home.TimeScheduleDto
import com.haeyum.schoolmate.data.Home.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import utils.TimeUtil
import utils.toTimeTable
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _timeScheduleInfo: MutableState<List<TimeSchedule>> = mutableStateOf(listOf())
    val timeScheduleInfo: State<List<TimeSchedule>> = _timeScheduleInfo

    private val _todoInfo: MutableState<List<Todo>> = mutableStateOf(listOf())
    val todoInfo: State<List<Todo>> = _todoInfo

    private val _notifyTimeInfo: MutableState<NotifyTime> = mutableStateOf(NotifyTime(false, "초기값", null, null, null))

    val notifyTimeInfo: State<NotifyTime> = _notifyTimeInfo



    fun getData() {

        _timeScheduleInfo.value = listOf(
            TimeSchedule(
                name = "수학",
                room = "e동 423호",
                startTime = TimeUtil.tomillisecond("15:30"),
                endTime = TimeUtil.tomillisecond("16:30")
            ),
            TimeSchedule(
                name = "영어",
                room = "e동 423호",
                startTime = TimeUtil.tomillisecond("19:30"),
                endTime = TimeUtil.tomillisecond("23:40")
            ),
        )

        _notifyTimeInfo.value = calNextTime(_timeScheduleInfo.value)


        _todoInfo.value = listOf(
            Todo(
                name = "13주차 교제 과제",
                subjectId = "ASKDKSA@2030",
                deadline = TimeUtil.tomillisecondLong("2022-12-02T15:30:00"),
                isDone = false
            ),
            Todo(
                name = "13주차 교제 과제",
                subjectId = "ASKDKSA@2030",
                deadline = TimeUtil.tomillisecondLong("2022-12-02T15:30:00"),
                isDone = false
            ),
            Todo(
                name = "13주차 교제 과제",
                subjectId = "ASKDKSA@2030",
                deadline = TimeUtil.tomillisecondLong("2022-12-02T15:30:00"),
                isDone = true
            ),
            Todo(
                name = "13주차 교제 과제",
                subjectId = "ASKDKSA@2030",
                deadline = TimeUtil.tomillisecondLong("2022-12-02T15:30:00"),
                isDone = true
            ),
        )
    }


    private fun calNextTime(todoInfo :  List<TimeSchedule>) : NotifyTime{

        if(todoInfo.isEmpty()) return NotifyTime(false, "금일은 수업이 존재하지 않습니다.", null, null, null)

        val curTime = TimeUtil.getCurTime()

        for (data in todoInfo){

            var diff = data.endTime - curTime

            if(diff < 0) {
                continue
            }
            return if(curTime > data.startTime){
                NotifyTime(true, "수업 종료까지",  TimeUtil.toStringKorean(curTime-data.startTime), TimeUtil.toStringAMS(data.startTime), data.name)
            } else{
                NotifyTime(true, "다음 수업까지", TimeUtil.toStringKorean(data.startTime-curTime), TimeUtil.toStringAMS(data.startTime), data.name)
            }
        }

        return NotifyTime(false, "금일의 모든 수업이 종료되었습니다.", null, null, null)
    }

}
