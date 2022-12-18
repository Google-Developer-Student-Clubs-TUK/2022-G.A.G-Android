package com.haeyum.schoolmate.ui.main.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.haeyum.data.model.timeSchedule.TimeScheduleEntity
import com.haeyum.domain.data.notifyTime.NotifyTime
import com.haeyum.schoolmate.data.Home.TimeScheduleDto
import com.haeyum.schoolmate.data.Home.TodoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import utils.TimeUtil
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _timeScheduleInfo: MutableState<List<TimeScheduleDto>> = mutableStateOf(listOf())
    val timeScheduleInfo: State<List<TimeScheduleDto>> = _timeScheduleInfo

    private val _todoInfo: MutableState<List<TodoDto>> = mutableStateOf(listOf())
    val todoInfo: State<List<TodoDto>> = _todoInfo

    private val _notifyTimeInfo: MutableState<NotifyTime> = mutableStateOf(NotifyTime(false, "초기값", null, null, null))

    val notifyTimeInfo: State<NotifyTime> = _notifyTimeInfo


    val temp: List<TimeScheduleEntity> = (listOf(
        TimeScheduleEntity(
            id = 1,
            name = "수학",
            room = "e동 423호",
            startTime = "15:30"
        ),
        TimeScheduleEntity(
            id = 1,
            name = "영어",
            room = "e동 423호",
            startTime = "19:30"
        ),
        TimeScheduleEntity(
            id = 1,
            name = "영어",
            room = "e동 423호",
            startTime = "23:40"
        ),
    ))

    fun getData() {
        _notifyTimeInfo.value = calNextTime(temp)


        _timeScheduleInfo.value = listOf(
            TimeScheduleDto(location = "산융 205호", major = "수학2", time = "11:30~12:20"),
            TimeScheduleDto(location = "산융 206호", major = "수학2", time = "11:30~12:20"),
        )

        _todoInfo.value = listOf(
            TodoDto(
                name = "13주차 교제 과제",
                major = "영어",
                time = "11.21 오전 11:00",
                is_submit = false
            ),
            TodoDto(
                name = "13주차 교제 과제",
                major = "영어",
                time = "11.21 오전 11:00",
                is_submit = false
            ),
            TodoDto(
                name = "13주차 교제 과제",
                major = "영어",
                time = "11.21 오전 11:00",
                is_submit = true
            ),
            TodoDto(
                name = "13주차 교제 과제",
                major = "영어",
                time = "11.21 오전 11:00",
                is_submit = true
            ),
        )
    }


    private fun calNextTime(todoInfo :  List<TimeScheduleEntity>) : NotifyTime{

        if(todoInfo.isEmpty()) return NotifyTime(false, "금일은 수업이 존재하지 않습니다.", null, null, null)

        val curTime = TimeUtil.getCurTime()
        var preName = "-1"

        for (data in todoInfo){

            var diff = TimeUtil.tomillisecond(data.startTime) - curTime

            if(diff < 0) {
                preName = data.name
                continue
            }
            val leftTime = TimeUtil.toString(diff)

            return if(data.name.equals(preName)){
                NotifyTime(true, "수업 종료까지", leftTime, data.startTime, data.name)
            } else{
                NotifyTime(true, "다음 수업까지", leftTime, data.startTime, data.name)
            }
        }

        return NotifyTime(false, "금일의 모든 수업이 종료되었습니다.", null, null, null)
    }

}
